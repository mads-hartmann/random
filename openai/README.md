Just playing around with the OpenAI APIs.

I have a database of links to blog posts, videos, etc. that I have read (or want to read). Most of them I have categorized into a set of tags.

I thought I would try to use OpenAIs models to

- create summaries (key points) of the individual resources ([example](https://platform.openai.com/playground/p/Z8oqEmAXGhdlDzk6ywQmbZSl?model=curie-instruct-beta-v2))
- semantic search over the content
- Classify the content based on my tags - potentially creating a [fine-tune](https://platform.openai.com/docs/guides/fine-tuning) based on the tags I have already manually applied.

```sh
npm install

# Set the env vars (look in 1password under the respective sites)
export OPENAI_API_KEY=""
export NOTION_TOKEN=""
export NOTION_DATABASE_ID=""

# For the node example scripts
node <script.js>

# For the TS scripts
npm run build
node dist/<script.js>

# If you want to debug.
#
#   1. add debugger; statement
#   2. run with --inspect-brk (see below)
#   3. Run the VSCode command "Debug: Attach to Node Process"
#
node --inspect-brk dist/<script.js>
```

## Interacting with the SQLite DB

For ad-hoc queries and running migraitons.

```sh
sqlite3 data/links.db
```

The schema is manually updated below whenever changed

```
CREATE TABLE links (
    title TEXT,
    link TEXT,
    topics_json TEXT,
    kind_json TEXT,
    scale NUMBER,
    author_json TEXT,
    notion_id TEXT,
    notion_url TEXT
);
```
