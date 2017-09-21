sources := \
	$(wildcard bin/*.re) \
	$(wildcard bin/**/*.re) \
	$(wildcard lib/*.re) \
	$(wildcard lib/**/*.re)

executables := \
	_build/default/bin/webserver.exe

build: $(executables)

run: build
	@_build/default/bin/webserver.exe

$(executables): $(sources)
	@jbuilder build

clean:
	jbuilder clean

docker-run:
	docker build -t reason-webserver:local .
	docker run -p 8080:8080 -it reason-webserver:local
