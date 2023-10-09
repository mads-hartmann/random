This is variant of [this blog post](https://fasterthanli.me/series/building-a-rust-service-with-nix/part-10) but for Go. See the blog post for details.

```sh
nix flake check
nix develop
# Build and run or just 'go run main.go'
go build 
./hello
```

TODO:
- Finish reading the blog post
- Add details for dev env etc (vscode)
- (note) Add note about this not being for build go, just the env