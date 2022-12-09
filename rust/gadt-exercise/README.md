Using [devenv](https://devenv.sh/getting-started/) to set up Rust tooling on-demand.

## Setup

Initial setup

```sh
cd rust/gadt-exercise

nix-env -iA cachix -f https://cachix.org/api/v1/install
cachix use devenv

nix-env -if https://github.com/cachix/devenv/tarball/v0.4

devenv init
```

## Dev

```
devenv shell
rustc src/main.rs && ./main
```

## Relevant resources

- [Rust manual](https://doc.rust-lang.org/book/title-page.html)
