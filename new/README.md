# New 📁

*This project is pure imagination for now. Haven't actually implemented
anything*

A CLI for creating new things. What kind of things? Anything that lives in
files.

## Installation

    brew install new

## Getting Started

`new` reads it's configuration from `~/.new/config.yml`. By default `new` stores
it's templates in `~/.new/templates/`.

`new` only ships with a single template. That template is a template for creating
new `new` templates.

Create your first template:

    new new

## Working on new

You need to have the OCaml package manager ([opam][opam]) installed and a
couple of opam packages as well.

    jbuilder
    reason

Now you can compile, run and test using the following

    make build
    make run
    make test

[opam]: https://opam.ocaml.org
