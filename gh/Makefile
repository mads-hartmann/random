sources := \
	$(wildcard gh/*.re)

executable := \
	_build/install/default/bin/gh

build: $(executable)

distclean clean:
	jbuilder clean

run: build
	$(executable) pullrequests

install: build

$(executable): $(sources) gh/jbuild
	jbuilder build @install
