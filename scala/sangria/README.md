# Sangria Playground

Playing around with Sangria a bit.

```shell
sbt run
curl -X POST localhost:8080/graphql \
    -H "Content-Type:application/json" \
    -d '{"query": "{hero {name, friends {name}}}"}'
```

Or start GraphiQL against `localhost:8080/graphql` with the query

```graphql
query a {
  hero {
    name
    friends {
      name
    }
  }
}
```
