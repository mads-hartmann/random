# TODO

- [ ] Config
  - [ ] Use environment variables like cheat does
- [ ] Decide on a templating language to use.
  - [ ] might be fun to roll my own using https://github.com/inhabitedtype/angstrom
        What does giter8 use?
- [ ] Figure out how to deal with multiple/single files  
  perhaps just say that it `~/.new/templates/foobar.template` then it's a single
  file, but if `~/.new/templates/foobar` is a directory then process and create
  all the files.
- [ ] Create a template for a bash script
- [ ] Create a template for a zsh completion script
- [ ] Make it possible to echo to stdout  
  This is interesting as it would allow editor integration.
