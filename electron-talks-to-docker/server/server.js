const express = require('express');

const docker = require('./docker')

const server = express();

server.get('/', (req, res) => {
    docker.containers()
        .then(containers => res.send(containers))
        .catch(e => console.error(e));
});

function start(port) {
  server.listen(port, () => {
      console.log(`Started node-server on ${port}`);
  });
}

module.exports = {
  start
}
