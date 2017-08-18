# new

*This project is pure imagination for now. Haven't actually implemented
anything*

`new` is a tool for creating and expanding templates for things. What kind of
things? Anything that lives in folders and files.

## Usage

```sh
new <template> [arg=value]*
new -e | --edit <template>
new --list
```

## Creating templates

The purpose of `new` is to ...

You can create a new template, or edit an existing one, using the `-e` or `--edit`
option to `new` like so

```sh
new --edit bash-script
```

By default `new` stores it's templates in `~/.new/templates/`, however you can
change that if you want to, see the [configuration](#configuration) section.

I'm a huge fan of [dotfiles][dotfiles] so I highly recommend add `~/.new` to
your dotfiles.

## Installation

    brew install new

## Configuration


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
[dotfiles]: http://dotfiles.github.io/
