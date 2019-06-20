
const express = require('express');
const axios = require('axios');
const tracing = require('@opencensus/nodejs');
const {ZipkinTraceExporter} = require('@opencensus/exporter-zipkin');
const {TraceContextFormat} = require('@opencensus/propagation-tracecontext');

const middleware = require('./middleware');

const port = process.env.PORT || 8080
const tracer = tracing.start({samplingRate: 1}).tracer;

tracer.registerSpanEventListener(new ZipkinTraceExporter({
  url: 'http://localhost:9411/api/v2/spans',
  serviceName: 'service-a',
  logLevel: 1 // show errors, if any
}));


tracing.start({propagation: new TraceContextFormat()});

const app = express();

app.get('/', middleware.tracing(tracer), async (request, response) => {
  const reply = await axios.get('http://localhost:8081/sleep/2000')
  // console.log(reply)
  response.status(reply.status);
  response.send(reply.data);
});

const listener = app.listen(port, () => {
  console.log(`Your app is listening on http://localhost:${listener.address().port}`);
});
