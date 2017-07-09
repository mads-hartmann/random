SHELL = /bin/bash

STAT := /usr/local/opt/coreutils/libexec/gnubin/stat

WATCH ?= 

bundle.js: source1.js source2.js
ifndef WATCH
	touch $@
else
	$(STAT) -c %Y $@
	@echo OMG
endif
