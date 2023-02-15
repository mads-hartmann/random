Just playing around with the OpenAI APIs.

I have a database of links to blog posts, videos, etc. that I have read (or want to read). Most of them I have categorized into a set of tags.

I thought I would try to use OpenAIs models to

- create summaries (key points) of the individual resources ([example](https://platform.openai.com/playground/p/Z8oqEmAXGhdlDzk6ywQmbZSl?model=curie-instruct-beta-v2))
- semantic search over the content
- Classify the content based on my tags - potentially creating a [fine-tune](https://platform.openai.com/docs/guides/fine-tuning) based on the tags I have already manually applied.

```sh
npm install
export OPENAI_API_KEY=...
node <script> # completion.js or classification.js
```
