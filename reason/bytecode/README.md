# Bytecode

Generate an OCaml bytecode executable that packages the `ocamlrun`
virtual machine as well as the OCaml bytecode.

The purpose of this is to generate an executable that runs on both Linux and
macOS.

```
jbuilder build hello.exe hello.bc
./_build/default/hello.exe
./_build/default/hello.bc
```

**NOTE**: This doesn't actually seem to work. The `hello.bc` executable
contains `#!/Users/hartmann/.opam/4.04.1/bin/ocamlrun` so it still requires
`ocamlrun` to be installed -- the virtual machine has not been embedded. The
`hello.exe` doesn't seem to work under another distribution as I would've
expected.

```
docker run -it -v $(pwd)/_build/default/hello.exe:/bin/hello-test:rw ubuntu bash                                                                                                         
root@8c5b0978509b:/# /bin/hello-test
bash: /bin/hello-test: cannot execute binary file: Exec format error
```
