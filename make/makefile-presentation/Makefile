build: index.lol

clean:
	rm index.lol

%.lol: %.txt
	$(PYTHON) lolify.py $< > $@

-include index.lol.d

include setup.mk
