// Automatically instruments using the default OpenCensus plugins.
const instrument = require('./instrument');
instrument.start('service-a');

const http = require('http');
const express = require('express');
const axios = require('axios');

const port = process.env.PORT || 8080
const app = express();

app.get('/', async (request, response) => {
  console.log(`headers: ${JSON.stringify(request.headers)}`);
  const reply = await axios.get('http://localhost:8081/sleep/2000')
  response.status(reply.status);
  response.send(reply.data);
});

http
  .createServer(app)
  .listen(port, () => {
    console.log(`Your app is listening on http://localhost:${port}`);
  });
