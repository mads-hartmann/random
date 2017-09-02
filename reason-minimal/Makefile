reason_files := \
	$(wildcard *.re)

build: $(reason_files) jbuild
	jbuilder build main.exe

clean:
	jbuilder clean

run: build
	_build/default/main.exe
