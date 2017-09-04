sources := \
	gh/jbuild \
	$(wildcard gh/*.re)

executable := \
	_build/install/default/bin/gh

build: $(executable)

clean:
	jbuilder clean

run: build
	$(executable) pullrequests

install: build
	@jbuilder install

uninstall distclean:
	@jbuilder uninstall

$(executable): $(sources)
	jbuilder build --dev
