# Electron 💬 Docker

This is a proof-of-concept for a tiny electron app that talks to Docker over a
socket.

    npm install
    npm run start

To be precise the electron app starts a small express server that talks to
Docker over a socket -- the electron app then talks that server. This is not
strictly needed (see previous commits in this folder) but as this is a
proof-of-concept for something we're build at Famly it made sense in our case.

The express server also contains a route that uses the webpack middleware to
compile the client javascript bundle on the fly. Again, there are multiple
ways to achieve the same result but bundling the client assets on the fly is
closer to where we want to take this application in the future ☺️
