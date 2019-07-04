# Observability with OpenCensus

This is a proof of concept I did to experiment with OpenCensus. It consists of two tiny node services where one depends on the other.

I'm using Prometheus for metrics and [Zipkin] for traces because they're free and easy to get up and running.

## Services

Here's a quick overview of the services we'll be running:

| Service | Description | Port | Endpoint |
| - | - | - | - |
| **Infrastructure** |  |  |  |
| Zipkin |  UI | 9411 |http://localhost:9411 |
| OpenCensus collector| Receiver | 55678
| OpenCensus collector | ZPages | 55679 | http://localhost:55679/debug/tracez |
| **Services** | | | |
|servica-a | Node service | 8080 | http://localhost:8080 |
|servica-b | Node service | 8081 | http://localhost:8081/sleep |

## Experiment

Start all the services:

```
docker-compose up
```

Start the node services.

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
or two.

[Zipkin]: https://zipkin.io

## Exporters

A quick note on each of the exporters that have been configured in this prototype.

### Datadog

The way the datadog exporter works is simply that it forwards traces to the
datadog agent you have running in your environment - so you still need to have
that running. It needs to have APM enabled.

TODO: Explain the other caveats.
