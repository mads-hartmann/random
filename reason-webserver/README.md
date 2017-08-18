# Example webserver in Reason

This is a minimal example of a webserver written in [Reason][reason] using the
[cohttp][cohttp] library. It uses [jbuild][jbuilder] as the build tool.

To run it locally run:

    opam install reason jbuilder async core cohttp cohttp-async
    make run

Or run it using Docker if you don't want to install any dependencies

    make run-docker
    # Note, right not it doesn't handle Ctrl-C properly 😅

[jbuilder]: http://jbuilder.readthedocs.io/en/latest/
[reason]: https://reasonml.github.io/
[cohttp]: https://github.com/mirage/ocaml-cohttp
