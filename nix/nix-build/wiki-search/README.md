```sh
nix-build wiki-search.nix
```

Inspect the result

```sh
ls -al result/bin
```

```sh
nix-shell shell-usage-example.nix

which wiki-search
/nix/store/w02nnahjrz1z5nhgv2wnprpi5rj1iaps-wiki-search/

wiki-search nix
```
