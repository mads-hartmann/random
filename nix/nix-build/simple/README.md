# Simple

From [Chapter 7. Working Derivation](https://nixos.org/guides/nix-pills/working-derivation.html)

```sh
nix repl

> :l <nixpkgs>
> simple = derivation { name = "simple"; builder = "${bash}/bin/bash"; args = [ ./simple_builder.sh ]; gcc = gcc; coreutils = coreutils; src = ./simple.c; system = builtins.currentSystem; }
> :b simple
This derivation produced the following outputs:
  out -> /nix/store/3abynr53qs78l1n23z2kkng7rhr9qray-simple
```

Based on the `out` above, you can now run the compiled program:

```sh
/nix/store/3abynr53qs78l1n23z2kkng7rhr9qray-simple/simple 
Simple!
```

And using `nix-build`

```sh
nix-build simple.nix
```