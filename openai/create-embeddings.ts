//
// Creates an SQLite database with embeddings from my Notions URL database
//

import { Client } from "@notionhq/client";

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

class LinkDB {
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

const db = new LinkDB(
  process.env.NOTION_TOKEN as string,
  process.env.NOTION_DATABASE_ID as string
);

const list = await db.list();

// console.log(list);
console.log(list.length);
