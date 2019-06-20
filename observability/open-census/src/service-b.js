// Automatically instruments using the default OpenCensus plugins.
const instrument = require('./instrument');
instrument.start('service-b');

const express = require('express');
const http = require('http');

const port = process.env.PORT || 8081
const app = express();

app.get('/sleep/:timeout', (request, response) => {
  console.log(`headers: ${JSON.stringify(request.headers)}`);
  response.status(200);
  response.send("okay from service-b");
});

http
  .createServer(app)
  .listen(port, () => {
    console.log(`Your app is listening on http://localhost:${port}`);
  });
