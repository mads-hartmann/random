From https://www.ertt.ca/nix/shell-scripts/

To run it

```sh
nix run .#
```

Build and inspect to see how this works:

```sh
nix build
ls -ogA result/bin
cat result/bin/my-script
```
