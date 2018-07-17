Trying to port [ocaml-graphql-server][ocaml-graphql-server] to Scala by
following Andreas' [blog post][blog-post].

The goal is to uphold the requirements described in the OCaml project:

> Only valid schemas should pass the type checker. If a schema compiles, the
> following holds:
>
> 1. The type of a field agrees with the return type of the resolve function.
> 2. The arguments of a field agrees with the accepted arguments of the resolve
>    function.
> 3. The source of a field agrees with the type of the object to which it
>    belongs.
> 4. The context argument for all resolver functions in a schema agree.

## Resources

- [TypedAPI](https://github.com/pheymann/typedapi)
  Might be useful. It's handling of routes are similar to how we're going to
  handle fields with arguments

[ocaml-graphql-server]: https://github.com/andreas/ocaml-graphql-server
[blog-post]: https://andreas.github.io/2017/11/29/type-safe-graphql-with-ocaml-part-1/
