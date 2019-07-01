# Observability with OpenCensus

This is a proof of concept I did to experiment with OpenCensus. It consists of two tiny node services where one depends on the other.

I'm using Prometheus for metrics and [Zipkin] for traces because they're free and easy to get up and running.

## Experiment

Start Prometheus, it will be running on http://localhost:9090

```
TODO
```

Start Zipkin, it will be running on http://localhost:9411

```
docker run -p 9411:9411 openzipkin/zipkin:2.14.1
```

_Alternatively_ start Honeycombs Zipkin compatible proxy to send the
traces to your Honeycomb account instead.

```
docker run \
    -p 9411:9411 \
    honeycombio/honeycomb-opentracing-proxy:1.109 \
        -d proof-of-concept \
        -k $HONEYCOMB_API_KEY \
        --debug
```

Run the services

```
npm install
node src/service-a.js # in one tab
node src/service-b.js # in another tab
```

Fire off a few requests

```
sh/send-requests.sh
```

Go to the Zipkin UI to check out the traces - they should appear within a minute
http://localhost:9411/zipkin/.

## TODO

### Traces

- [x] Create a service B and let service A invoke it.
- [x] Get the trace context propagation to work
- [x] README
  - [x] Add commands to run the two node services
  - [x] Add a shell command to hit the endpoints a few times
  - [x] Describe how to read the traces in the UI to verify it works

### Metrics

- [] Add metrics to the index.js example in the service
     Metrics aren't as important right now for us as we're mostly wanting traces right now.
- [] README
  - [] Add docker run commands for Prometheus
  - [] Describe how to read the metrics in the UI to verify it works

[Zipkin]: https://zipkin.io


## Questions

- How does the trace context library work? Does it patch http?
