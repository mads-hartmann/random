// Automatically instruments using the default OpenCensus plugins.
const instrument = require('./instrument');
instrument.start('service-a', 55676);

const http = require('http');
const express = require('express');
const axios = require('axios');

const port = process.env.PORT || 8080
const app = express();

app.get('/', async (request, response) => {

  const sleepMS1 = Math.floor(Math.random() * Math.floor(500))
  const sleepMS2 = Math.floor(Math.random() * Math.floor(300))

  await new Promise((resolve, reject) => setTimeout(resolve, sleepMS1))

  const reply = await axios.get(`http://localhost:8081/sleep/${sleepMS2}`)
  response.status(reply.status);
  response.send(reply.data);
});

http
  .createServer(app)
  .listen(port, () => {
    console.log(`Your app is listening on http://localhost:${port}`);
  });
