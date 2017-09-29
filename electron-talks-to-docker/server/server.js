const express = require('express');
const webpack = require('webpack');
const webpackDevMiddleware = require('webpack-dev-middleware');

const docker = require('./docker');

const server = express();
const compiler = webpack(require('../webpack.config'));

server.use(
  webpackDevMiddleware(compiler, {
    publicPath: '/webpack/',
    lazy: true
  })
);

server.get('/', (req, res) => {
  docker
    .containers()
    .then(containers => res.send(containers))
    .catch(e => console.error(e));
});

server.use('/assets', express.static('assets'));

server.get('/remove/:container_id', (req, res) => {
  docker
    .request(`/containers/${req.params.container_id}?v=1`, 'DELETE')
    .then(response => {
      res.send('OK');
    })
    .catch(e => console.log(e));
});

server.get('/start/:container_id', (req, res) => {
  docker
    .request(`/containers/${req.params.container_id}/start`, 'POST')
    .then(response => {
      res.send('OK');
    })
    .catch(e => console.log(e));
});

server.get('/stop/:container_id', (req, res) => {
  docker
    .request(`/containers/${req.params.container_id}/stop`, 'POST')
    .then(response => {
      res.send('OK');
    })
    .catch(e => console.log(e));
});

server.get('/kill/:container_id', (req, res) => {
  const id = req.params.container_id;
  docker
    .request(`/containers/${id}/wait?condition=removed`, 'POST')
    .then(response => {
      console.log('Got response', response);
      res.send(response);
    })
    .catch(e => {
      console.log('Waiting failed failed', e);
      res.status(500).send(e);
    });

  docker.request(`/containers/${id}?force=true`, 'DELETE').catch(e => {
    console.log('Deletion failed', e);
    res.status(500).send(e);
  });
});

const port = 9000;
server.listen(port, () => {
  console.log(`Started node-server on ${port}`);
});
