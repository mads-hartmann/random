const tracing = require('@opencensus/nodejs');
const {TraceContextFormat} = require('@opencensus/propagation-tracecontext');
const {OCAgentExporter} = require('@opencensus/exporter-ocagent');

module.exports = {
  /**
   * This must be invoked before other imports. It uses the default OpenCensus plugins
   * to monkey-patch various modules in order to automatically do context propagation.
   */
  start: (serviceName) => {
    tracing.start({
      exporter: new OCAgentExporter({
        serviceName: serviceName,
        host: 'localhost',
        port: 55678,
      }),
      propagation: new TraceContextFormat(),
      samplingRate: 1,
    });
    tracing.tracer.registerSpanEventListener({
      onStartSpan(span) {},
      onEndSpan(span) {
        span.addAttribute('service', serviceName)
        // Datadog is picky about span names and doesn't allow
        // https://github.com/DataDog/opencensus-go-exporter-datadog/pull/36
        // console.log(span)
        const method = span.attributes['http.method']
        const route = span.attributes['http.route']
        if (method && route) {
          span.name = `${method}: ${route}`
        }
      }
    })
  }
}
