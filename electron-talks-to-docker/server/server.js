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

server.get('/remove/:container_id', (req, res) => {
    docker.request(`/containers/${req.params.container_id}?v=1`, 'DELETE')
        .then(response => {
            res.send('OK');
        })
        .catch(e => console.log(e))
});

server.get('/start/:container_id', (req, res) => {
    docker.request(`/containers/${req.params.container_id}/start`, 'POST')
        .then(response => {
            res.send('OK');
        })
        .catch(e => console.log(e))
});

server.get('/stop/:container_id', (req, res) => {
    docker.request(`/containers/${req.params.container_id}/stop`, 'POST')
        .then(response => {
            res.send('OK');
        })
        .catch(e => console.log(e))
});

function start(port) {
  server.listen(port, () => {
      console.log(`Started node-server on ${port}`);
  });
}

module.exports = {
  start
}
