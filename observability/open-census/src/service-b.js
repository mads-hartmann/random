
const express = require('express');
const tracing = require('@opencensus/nodejs');
const {ZipkinTraceExporter} = require('@opencensus/exporter-zipkin');
const {TraceContextFormat} = require('@opencensus/propagation-tracecontext');

const middleware = require('./middleware');

const port = process.env.PORT || 8081

tracing.start({
  exporter: new ZipkinTraceExporter({
    url: 'http://localhost:9411/api/v2/spans',
    serviceName: 'service-b',
    // logLevel: 1 // show errors, if any
  }),
  propagation: new TraceContextFormat(),
  samplingRate: 1,
});

const app = express();

app.get('/sleep/:timeout', (request, response) => {
  console.log(`headers: ${JSON.stringify(request.headers)}`);
  response.status(200);
  response.send("okay from service-b");
});

// const listener = app.listen(port, () => {
//   console.log(`Your app is listening on http://localhost:${listener.address().port}`);
// });

const http = require('http');
app.set('port', port);
const server = http.createServer(app);
server.listen(port);
