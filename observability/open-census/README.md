# Observability with OpenCensus

This is an experiment I did while researching various observability tools so we
could decide on which tools to adopt.

**caveat:** Due to a limitation/bug in the jaeger and datadog exporters this example is running an agent/collector per service - the bug causes them to drop the service name you've specified in your application and will instead show up as *opencensus-app* which isn't very helpful when you're trying to debug performance issues with your services. In the example I'm running one collector and one agent - the collector could just as well have been an agent but I wanted to see the collector in action as well.

## Services

Here's a quick overview of the services we'll be running:

| Service | Description | Port | Endpoint |
| - | - | - | - |
| **Infrastructure** |  |  |  |
| Zipkin |  UI | 9411 |http://localhost:9411 |
| **Service a** | | | |
|servica-a | Node service | 8080 | http://localhost:8080 |
| OpenCensus agent| Receiver | 55676 |
| OpenCensus agent | ZPages | 55677 | http://localhost:55677/debug/tracez |
| **Service b** | | | |
|servica-b | Node service | 8081 | http://localhost:8081/sleep |
| OpenCensus collector| Receiver | 55678 |
| OpenCensus collector | ZPages | 55679 | http://localhost:55679/debug/tracez |

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

Here's the [issue](https://github.com/census-instrumentation/opencensus-service/issues/551#issuecomment-493143844) that explains the need for `service_name`.
