# Example webserver in OCaml

This is a minimal example of a webserver written in OCaml using the
[cohttp][cohttp] library.

It uses [jbuild][jbuilder] as the build tool.

To run it locally run:

    opam install jbuilder async core cohttp cohttp-async
    make run

Or run it using Docker (doesn't require ocaml/opam locally)

    make run-docker

## TODO

- [ ] Make sure that SIGINT SIGHUP are handled properly
      Right now you can't quit the server when running in Docker 😂
- [ ] Create a nice edit->compile->restart experience.

[jbuilder]: http://jbuilder.readthedocs.io/en/latest/
[cohttp]: https://github.com/mirage/ocaml-cohttp
