# Bash Language Server

## TODO

- [ ] Parse all files in the repo on init
- [ ] Perform incremental updates instead
- [ ] Maybe just use an index of identifier => uri and then run through the AST
  in those files on every search? That would be less state and I doubt people
  have HUGE bash files.
- [x] Jump to definition (in file)
- [x] Jump to definition (in project)
- [ ] Look up man pages / --help to show inline help

## How to run locally

In the root

* `npm install` to initialize the extension and the server
* `npm run compile` to compile the extension and the server
* open this folder in VS Code. In the Debug viewlet, run 'Launch Client' from drop-down to launch the extension and attach to the extension.
* create a file `test.txt`, and type `typescript`. You should see a validation error.
* to debug the server use the 'Attach to Server' launch config.
* set breakpoints in the client or the server.

### Running tests

    cd server
    npm run jest
