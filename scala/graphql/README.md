Trying to implement a tiny graphql server in Scala based on
this [blog post](https://andreas.github.io/2017/11/29/type-safe-graphql-with-ocaml-part-1/).

```
scalars (leafs)
    source type
    serializer
objects (non-leaf nodes)
    source type
    collection of fields (a field is an edge)
field
    name
    resolve function
```

> Furthermore, the definition needs to support introspection, so we
> cannot just have a graph of closures, which hide the structure of
> the graph itself
