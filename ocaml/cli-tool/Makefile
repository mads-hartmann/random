ocaml_files := \
	$(wildcard src/*.ml) \
	$(wildcard src/**/*.ml)

build: $(ocaml_files) jbuild
	jbuilder build main.exe

run: build
	_build/default/main.exe
