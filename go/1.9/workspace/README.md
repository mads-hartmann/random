# Go multi module workspaces

Goal of this experiment:

- Use [Go Workspaces](https://go.dev/doc/tutorial/workspaces) to manage two modules `hello` and `util` where `hello` depends on `util`.
- See how the gopls support works. Their docs for it are [here](https://github.com/golang/tools/blob/master/gopls/doc/workspace.md).

TL;DR - it works great, with the small caveat that the `go.work` file has to be at the root of your VSCode workspace, so in this repository where it isn't I had to `code .` to change the workspace root to this folder.

---

Creating the files and showing it works from the terminal - followed this guide with some modifications ["Tutorial: Getting started with multi-module workspaces"](https://go.dev/doc/tutorial/workspaces).

```sh
# From this directory

# Activate the nix shell
nix-shell

#
# Create hello module
#
mkdir hello
cd hello/
go mod init example.com/hello
# Copied the contents into the file
go run example.com/hello

#
# Create workspace
#
cd ..
go work init ./hello
# Show that you can run from workspace dir
go run example.com/hello
Hello

#
# Create util package
#
mkdir util
cd util/
go mod init example.com/util
# Put contents in util.go

#
# Add 'use ./util' to go.work
#
cd ..
# Show that it works
go run example.com/hello
HELLO
```

To make vscode happy, the go.work file has to be in the root, so in Gitpod I'd have to `code .` in this folder `random/go/1.9/workspace` but then it works nicely.
