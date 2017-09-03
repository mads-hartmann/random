# Check your current Github Pull requests

Tiny CLI to help you keep track of your current pull request responsibilities.

```sh
opam switch install 4.04.0
opam pin add reason 1.13.7
opam install jbuilder core yojson ssl tls cohttp async cohttp-lwt cohttp-lwt-unix lwt_ssl
make run
```

## Configuration

The CLI is configured through environment variables.

- `GITHUB_OAUTH_TOKEN`: You can generate a token [here][gh-token]. It just needs
  access to your user data.
- `GITHUB_USERNAME`: The Github username to check pull requests for.

## Installing

```sh
opam pin add gh .
```

## Uninstalling

```sh
opam uninstall gh
opam pin remove gh
```

[gh-token]: https://github.com/settings/tokens
