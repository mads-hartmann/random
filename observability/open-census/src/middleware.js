module.exports = {
  // TODO: Can't use a app.use middleware here as request.route is added by a middleware that
  //       is invoked after my middleware it seems...
  tracing: (tracer) => (request, response, next) => {
    tracer.startRootSpan({name: request.route.path}, rootSpan => {
      next();
      response.on('finish', () => {
        rootSpan.end();
      });
    });
  }
}
