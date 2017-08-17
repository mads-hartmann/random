ocaml_files := \
	$(wildcard src/*.ml) \
	$(wildcard src/**/*.ml)

build: $(ocaml_files) jbuild
	jbuilder build main.exe

run: build
	_build/default/main.exe

docker-run:
	docker build -t ocaml-webserver:local .
	docker run -p 8080:8080 -it ocaml-webserver:local
