//
// Creates an SQLite database with embeddings from my Notions URL database
//

import { Client } from "@notionhq/client";
import got from "got";
import sqlite from "sqlite3";

type Select = {
  id: string;
  name: string;
  color: string;
};

type NotionMetadata = {
  id: string;
  url: string;
};

type Link = {
  link: string;
  title: string;
  topics: Select[];
  kind?: Select;
  scale?: Number;
  author?: Select[];
  notionMetadata: NotionMetadata;
};

class NotionLinkDB {
  private notion: Client;
  private databaseID: string;
  constructor(token: string, databaseID: string) {
    this.databaseID = databaseID;
    this.notion = new Client({
      auth: token,
    });
  }

  async get(pageID: string): Promise<Link> {
    const response = await this.notion.pages.retrieve({
      page_id: pageID,
    });
    return this.parse(response);
  }

  async list(): Promise<Link[]> {
    const links: Link[] = [];

    let hasMore = true;
    let cursor: string | undefined = undefined;

    while (hasMore) {
      const response = await this.notion.databases.query({
        page_size: 100,
        start_cursor: cursor,
        database_id: this.databaseID,
        sorts: [
          {
            property: "Added at",
            direction: "descending",
          },
        ],
      });

      response.results.forEach((page: any) => links.push(this.parse(page)));

      hasMore = response.has_more;
      cursor = response.next_cursor;
    }

    return links;
  }

  private parse(page: any): Link {
    debugger;
    const title = page.properties["Title"]["title"][0]["plain_text"];
    const link = page.properties["Link"]["url"];
    const author = page.properties["Author"]["multi_select"].map(
      (tag: any) => ({
        id: tag.id,
        name: tag.name,
        color: tag.color,
      })
    );
    const scale = page.properties["Scale"]["number"];
    const kind = page.properties["Kind"]["select"];
    const topics = page.properties["Topic"]["multi_select"].map((tag: any) => ({
      id: tag.id,
      name: tag.name,
      color: tag.color,
    }));
    return {
      title,
      link,
      topics,
      kind,
      author,
      scale,
      notionMetadata: {
        id: page.id,
        url: page.url,
      },
    };
  }
}

const notionDB = new NotionLinkDB(
  process.env.NOTION_TOKEN as string,
  process.env.NOTION_DATABASE_ID as string
);

const s = sqlite.verbose();
const sqliteDB = new s.Database("data/links.db");

const list = await notionDB.list();

sqliteDB.serialize(() => {
  const stmt = sqliteDB.prepare(`
    INSERT INTO links (title, link, topics_json, kind_json, scale, author_json, notion_id, notion_url)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
  `);
  list.forEach((link: Link) => {
    stmt.run(
      link.title,
      link.link,
      JSON.stringify(link.topics),
      JSON.stringify(link.kind),
      link.scale,
      JSON.stringify(link.author),
      link.notionMetadata.id,
      link.notionMetadata.url
    );
  });
  stmt.finalize();
});

// TODO:
// - Added "read at" to the db
// - Add HTML content to the DB (should be added by another script)
// - Add OpenAI embeddings to the DB (should be added by another script)
//

sqliteDB.close();
