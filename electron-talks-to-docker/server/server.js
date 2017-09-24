const express = require('express');
const webpack = require('webpack');
const webpackDevMiddleware = require('webpack-dev-middleware');

const docker = require('./docker')


const server = express();
const compiler = webpack(require('../webpack.config'));

server.use(webpackDevMiddleware(compiler, {
    publicPath: '/webpack/',
    lazy: true
}));

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
