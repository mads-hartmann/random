const tracing = require('@opencensus/nodejs');
const {ZipkinTraceExporter} = require('@opencensus/exporter-zipkin');
const {TraceContextFormat} = require('@opencensus/propagation-tracecontext');

module.exports = {
  /**
   * This must be invoked before other imports. It uses the default OpenCensus plugins
   * to monkey-patch various modules in order to automatically do context propagation.
   */
  start: (serviceName) => {
    tracing.start({
      exporter: new ZipkinTraceExporter({
        // Use http://localhost:9411/api/v2/spans if you're sending to zipkin directly (The honeycomb proxy seems to use v1)
        url: 'http://localhost:9411/api/v1/spans',
        serviceName,
        logLevel: 1 // show errors, if any
      }),
      propagation: new TraceContextFormat(),
      samplingRate: 1,
    });
  }
}
