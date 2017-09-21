FROM ocaml/opam:alpine

# Install system dependencies
RUN sudo apk add --no-cache m4

RUN mkdir -p /home/opam/webserver
WORKDIR /home/opam/webserver

# Install library dependencies
RUN opam install reason jbuilder async core cohttp cohttp-async

# Compile binary
COPY jbuild-workspace.docker .
COPY webserver.opam .
COPY bin bin
COPY lib lib

RUN sudo chown -R opam bin && sudo chown -R opam lib
RUN opam config exec -- jbuilder build

EXPOSE 8080
CMD ["_build/default/bin/webserver.exe"]
