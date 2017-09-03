reason_files := \
	$(wildcard *.re)

build: $(reason_files) jbuild
	jbuilder build main.exe

distclean clean:
	jbuilder clean

run: build
	_build/default/main.exe

install:
	jbuilder build @install
