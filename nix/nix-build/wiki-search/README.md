TODO: Trying to create a nix-build example which is just a bash script that has a dependency on curl and jq.

Was following this guide but there might be better ones: https://nix-tutorial.gitlabpages.inria.fr/nix-tutorial/first-package.html


TODO: It doesn't build yet

```sh
nix-build wiki-search.nix
```

TODO:
- Add a nix-shell example which uses the packages and show that wiki-search is available as a "standalone binary"
- Show that the script references curl and jq from the store (e.g. that Nix will replace the invocations)
