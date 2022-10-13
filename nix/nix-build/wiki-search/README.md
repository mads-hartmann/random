TODO: Using https://www.sam.today/blog/creating-a-super-simple-derivation-learning-nix-pt-3/

TODO: It doesn't build yet

```sh
nix-build wiki-search.nix
```

TODO:
- Add a nix-shell example which uses the packages and show that wiki-search is available as a "standalone binary"
- Show that the script references curl and jq from the store (e.g. that Nix will replace the invocations)
