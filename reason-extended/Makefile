sources := \
	$(wildcard bin/*.re) \
	$(wildcard bin/**/*.re) \
	$(wildcard lib/*.re) \
	$(wildcard lib/**/*.re)

executables := \
	_build/default/bin/hello.exe

build: $(executables)

run: build
	@_build/default/bin/hello.exe

clean:
	jbuilder clean

$(executables): $(sources)
	@jbuilder build
