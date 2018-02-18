# Bash Language Server

## TODO

- [ ] Fix README. This is still copy-pasted mostly.
- [ ] Jump to definition
- [ ] Look up man pages / --help to show inline help

## How to run locally

* `npm install` to initialize the extension and the server
* `npm run compile` to compile the extension and the server
* open this folder in VS Code. In the Debug viewlet, run 'Launch Client' from drop-down to launch the extension and attach to the extension.
* create a file `test.txt`, and type `typescript`. You should see a validation error.
* to debug the server use the 'Attach to Server' launch config.
* set breakpoints in the client or the server.

### Running tests

    cd server
    npm run jest
