# Observability with OpenCensus

This is a proof of concept I did to experiment with OpenCensus. It consists of two tiny node services where one depends on the other.

I'm using Prometheus for metrics and [Zipkin] for traces because they're free and easy to get up and running.

## Experiment

Start Prometheus, it will be running on http://localhost:9090

```

```

Start Zipkin, it will be running on http://localhost:9411

```
docker run -p 9411:9411 openzipkin/zipkin:2.14.1
```

## TODO

### Traces

- [] Create a service B and let service A invoke it.
- [] Get the trace context propagation to work
- [] README
  - [] Add commands to run the two node services
  - [] Add a shell command to hit the endpoints a few times
  - [] Describe how to read the traces in the UI to verify it works

### Metrics

- [] Add metrics to the index.js example in the service
     Metrics aren't as important right now for us as we're mostly wanting traces right now.
- [] README
  - [] Add docker run commands for Prometheus
  - [] Describe how to read the metrics in the UI to verify it works

[Zipkin]: https://zipkin.io


## Questions

- How does the trace context library work? Does it patch http?
