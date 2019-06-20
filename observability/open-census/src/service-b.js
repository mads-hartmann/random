
const express = require('express');
const tracing = require('@opencensus/nodejs');
const {ZipkinTraceExporter} = require('@opencensus/exporter-zipkin');
const {TraceContextFormat} = require('@opencensus/propagation-tracecontext');

const middleware = require('./middleware');

const port = process.env.PORT || 8081
const tracer = tracing.start({samplingRate: 1}).tracer;

tracer.registerSpanEventListener(new ZipkinTraceExporter({
  url: 'http://localhost:9411/api/v2/spans',
  serviceName: 'service-b',
  logLevel: 1 // show errors, if any
}));

tracing.start({propagation: new TraceContextFormat()});

const app = express();

app.get('/sleep/:timeout', middleware.tracing(tracer), (request, response) => {
  response.status(200);
  response.send("okay from service-b");
});

const listener = app.listen(port, () => {
  console.log(`Your app is listening on http://localhost:${listener.address().port}`);
});
