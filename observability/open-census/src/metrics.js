//
// Mads: Example of exporting metrics
// See guide https://opencensus.io/quickstart/nodejs/metrics/#1
//

const { globalStats, MeasureUnit, AggregationType, TagMap } = require('@opencensus/core');
const { PrometheusStatsExporter } = require("@opencensus/exporter-prometheus");

const fs = require('fs');
const readline = require('readline');

// Enable OpenCensus exporters to export metrics to Prometheus Monitoring.
const exporter = new PrometheusStatsExporter({
  // Metrics will be exported on https://localhost:{port}/metrics
  port: 9464,
  startServer: true
});

// Pass the created exporter to global Stats
globalStats.registerExporter(exporter);

// The latency in milliseconds
const mLatencyMs = globalStats.createMeasureDouble("repl/latency", MeasureUnit.MS, "The latency in milliseconds per REPL loop");

// Counts/groups the lengths of lines read in.
const mLineLengths = globalStats.createMeasureInt64("repl/line_lengths", MeasureUnit.BYTE, "The distribution of line lengths");

// Creates a stream to read our file
const stream = fs.createReadStream("./test.txt");

// Creates an interface to read and process our file line by line
const lineReader = readline.createInterface({ input: stream });

const methodTagKey = { name: "method" };
const statusTagKey = { name: "status" };
const errorTagKey = { name: "error" };

// Create and Register the view.
const latencyView = globalStats.createView(
  "demo/latency",
  mLatencyMs,
  AggregationType.DISTRIBUTION,
  [methodTagKey, statusTagKey, errorTagKey],
  "The distribution of the latencies",
  // Bucket Boundaries:
  // [>=0ms, >=25ms, >=50ms, >=75ms, >=100ms, >=200ms, >=400ms, >=600ms, >=800ms, >=1s, >=2s, >=4s, >=6s]
  [0, 25, 50, 75, 100, 200, 400, 600, 800, 1000, 2000, 4000, 6000]
);
globalStats.registerView(latencyView);

// Create and Register the view.
const lineCountView = globalStats.createView(
  "demo/lines_in",
  mLineLengths,
  AggregationType.COUNT,
  [methodTagKey, statusTagKey],
  "The number of lines from standard input"
);
globalStats.registerView(lineCountView);

// Create and Register the view.
const lineLengthView = globalStats.createView(
  "demo/line_lengths",
  mLineLengths,
  AggregationType.DISTRIBUTION,
  [methodTagKey, statusTagKey],
  "Groups the lengths of keys in buckets",
  // Bucket Boudaries:
  // [>=0B, >=5B, >=10B, >=15B, >=20B, >=40B, >=60B, >=80, >=100B, >=200B, >=400, >=600, >=800, >=1000]
  [0, 5, 10, 15, 20, 40, 60, 80, 100, 200, 400, 600, 800, 1000]
);
globalStats.registerView(lineLengthView);

// The begining of our REPL loop
let startTime = new Date();
let endTime;
// REPL is the read, evaluate, print and loop
lineReader.on("line", function (line) {       // Read
  // Registers the Tags for our measurements
  const tags = new TagMap();
  tags.set(methodTagKey, { value: "REPL" });
  tags.set(statusTagKey, { value: "OK" });

  try {
    const processedLine = processLine(line);    // Evaluate
    console.log(processedLine);                // Print

    globalStats.record([{
      measure: mLineLengths,
      value: processedLine.length
    }, {
     measure: mLatencyMs,
     value: (new Date()) - startTime.getTime()
   }], tags);
  } catch (err) {
    const errTags = new TagMap();
    errTags.set(methodTagKey, { value: "REPL" });
    errTags.set(statusTagKey, { value: "ERROR" });
    errTags.set(errorTagKey, { value: err.message });

    globalStats.record([{
      measure: mLatencyMs,
      value: (new Date()) - startTime.getTime()
    }], errTags);
  }


  // Restarts the start time for the REPL
  startTime = new Date();
});

/**
 * Takes a line and process it.
 * @param {string} line The line to process
 */
function processLine(line) {
  // Currently, it just capitalizes it.
  return line.toUpperCase();
}
