
const express = require('express');
const axios = require('axios');
const tracing = require('@opencensus/nodejs');
const {ZipkinTraceExporter} = require('@opencensus/exporter-zipkin');
const {TraceContextFormat} = require('@opencensus/propagation-tracecontext');

const middleware = require('./middleware');

const port = process.env.PORT || 8080

tracing.start({
  exporter: new ZipkinTraceExporter({
    url: 'http://localhost:9411/api/v2/spans',
    serviceName: 'service-a',
    // logLevel: 1 // show errors, if any
  }),
  propagation: new TraceContextFormat(),
  samplingRate: 1,
});

const app = express();

app.get('/', async (request, response) => {
  console.log(`headers: ${JSON.stringify(request.headers)}`);
  const reply = await axios.get('http://localhost:8081/sleep/2000')
  // console.log(reply)
  response.status(reply.status);
  response.send(reply.data);
});

// const listener = app.listen(port, () => {
//   console.log(`Your app is listening on http://localhost:${listener.address().port}`);
// });

const http = require('http');
app.set('port', port);
const server = http.createServer(app);
server.listen(port);
