# Check your current Github Pull requests

Tiny CLI to help you keep track of your current pull request responsibilities.

## Configuration

The CLI is configured through environment variables.

- `GITHUB_OAUTH_TOKEN`: You can generate a token [here][gh-token]. It just needs
  access to your user data.
- `GITHUB_USERNAME`: The Github username to check pull requests for.

## Development

```sh
opam switch install 4.04.1
opam pin add reason 1.13.7
opam install ssl tls
opam install jbuilder
opam install core yojson async lwt lwt_ssl cohttp cohttp-lwt cohttp-lwt-unix
make run
```

## Installing

```sh
make install
```

## Uninstalling

```sh
make uninstall
```

[gh-token]: https://github.com/settings/tokens
