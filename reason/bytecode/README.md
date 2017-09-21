# Bytecode

Generate an OCaml bytecode executable that packages the `ocamlrun`
virtual machine as well as the OCaml bytecode.

The purpose of this is to generate an executable that runs on both Linux and
macOS.

    jbuilder build
    _build/install/default/bin/hello
