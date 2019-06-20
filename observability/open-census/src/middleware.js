/**
 * An experiment to do a more manual instrumentation. It's not currently used in the services.
 *
 * TODO: It doesn't do context propagation right now. Could I manually invoke tracer.propagation?
 *
 * NOTE: This middleware can't be used with app.use as request.route is added by a middleware that
 * is invoked after this middleware (it seems).
 */
module.exports = {
  tracing: (tracer) => (request, response, next) => {
    tracer.startRootSpan({name: request.route.path}, rootSpan => {
      next();
      response.on('finish', () => {
        rootSpan.end();
      });
    });
  }
}
