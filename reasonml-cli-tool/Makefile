reason_files := \
	$(wildcard *.re)

build: $(reason_files) jbuild
	jbuilder build main.exe

run: build
	_build/default/main.exe
