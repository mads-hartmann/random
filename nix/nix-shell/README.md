# nix-shell

## `--pure`

> If this flag is specified, the environment is almost entirely cleared before the interactive shell is started, so you get an environment that more closely corresponds to the “real” Nix build. A few variables, in particular HOME, USER and DISPLAY, are retained.

Without using `--pure` it finds the `go` version that ships with the Docker image provided by Gitpod by default:

```sh
$ nix-shell empty.nix
[nix-shell]$ which go
/home/gitpod/go/bin/go
```

Now using `--pure` it can't even find which

```sh
$ nix-shell --pure empty.nix
[nix-shell]$ which go
bash: which: command not found
```
