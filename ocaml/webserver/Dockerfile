FROM ocaml/opam:alpine

# Install system dependencies
RUN sudo apk add --no-cache m4

RUN mkdir -p /home/opam/webserver
WORKDIR /home/opam/webserver

# Install library dependencies
COPY jbuild .
RUN opam install jbuilder async core cohttp cohttp-async

# Compile binary
COPY main.ml .
RUN opam config exec -- jbuilder build main.exe

EXPOSE 8080
ENTRYPOINT []
CMD ["_build/default/main.exe"]
