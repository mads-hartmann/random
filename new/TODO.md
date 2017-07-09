# TODO

- [ ] Config
  - [ ] Make it possible to specify the config path using `--config`
  - [ ] Fall back to a default config is one isn't present
- [ ] Decide on a templating language to use
- [ ] Figure out how to deal with multiple/single files  
  perhaps just say that it `~/.new/templates/foobar.template` then it's a single
  file, but if `~/.new/templates/foobar` is a directory then process and create
  all the files.
- [ ] Create a template for a bash script
- [ ] Create a template for a zsh completion script
- [ ] Make it possible to echo to stdout  
  This is interesting as it would allow editor integration.
