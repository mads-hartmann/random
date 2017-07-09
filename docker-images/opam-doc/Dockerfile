# To use it:
#
# docker build -t="mads379/opam-doc:latest" .
# docker run -p 8000:8000 -i -t mads379/opam-doc:latest
#
# On OS X you need a mapping for port 8000 also then

FROM ubuntu:14.10
MAINTAINER Mads Hartmann <mads379@gmail.com>

# Install all the debian packages we need for opam to work.
RUN apt-get update
RUN apt-get install --yes --fix-missing libc-dev
RUN apt-get install --yes ocaml
RUN apt-get install --yes opam
RUN apt-get install --yes m4
RUN apt-get install --yes camlp4-extra
RUN apt-get install --yes ocaml-findlib

# Couldn't get cohttp-server to work even I installed
# the required packages as explained in:
#
#    https://github.com/ocamllabs/opam-doc
#
# So instead we use the python webserver.
RUN apt-get update              # No idea why I need to do this again?
RUN apt-get install --yes python-dev

# Install the requred opam packages.
RUN opam init
RUN eval `opam config env`
ENV OPAMDOC_BASE_URI http://192.168.59.103:8000

# Create the documentation and start the server
RUN eval `opam config env` && opam doc -n yojson batteries netamqp2 msgpack
CMD /.opam/system/bin/opam-doc-serve
