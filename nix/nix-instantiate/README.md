# nix-instantiate

Using `--eval` means it "Just parse and evaluate the input files, and print the resulting values on standard output. No instantiation of store derivations takes place." which is useful when playing around with core data types, language constructs and so on.

From `stdin`

```sh
echo "2+2" | nix-instantiate --eval -
```

From a file

```
nix-instantiate --eval add.nix
nix-instantiate --eval attribute-set.nix
```
