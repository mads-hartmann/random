const express = require("express");
const http = require("http");
const url = require("url");
const bodyParser = require("body-parser");
const webpack = require("webpack");
const webpackDevMiddleware = require("webpack-dev-middleware");
const WebSocket = require("ws");
var stream = require("stream");

const app = express();
const server = http.createServer(app);
const wss = new WebSocket.Server({ server });
const compiler = webpack(require("../webpack.config"));

app.use(
  webpackDevMiddleware(compiler, {
    publicPath: "/webpack/",
    lazy: true
  })
);

const Docker = require("dockerode");
const dockerode = new Docker({ socketPath: "/var/run/docker.sock" });

app.use(bodyParser.json());

wss.on("connection", function connection(ws, req) {
  const location = url.parse(req.url, true);
  const container = dockerode.getContainer(location.query.containerId);
  var logStream = new stream.PassThrough();

  logStream.on("data", function(chunk) {
    ws.send(chunk.toString("utf8"));
  });

  container.logs(
    {
      follow: true,
      tail: 5,
      stdout: true,
      stderr: true
    },
    function(err, stream) {
      if (err) {
        return console.error(err.message);
      }
      container.modem.demuxStream(stream, logStream, logStream);
    }
  );

  ws.on("message", function incoming(message) {
    console.log("received: %s", message);
  });
});

app.get("/", (req, res) => {
  dockerode
    .listContainers({all: true})
    .then(containers => containers.map(container => {
      const name = container.Names.join(" ").replace("/", "");
      return {
        name: name,
        container_id: container.Id,
        image: container.Image,
        // created|restarting|running|removing|paused|exited|dead
        state: container.State,
        status: container.Status,
        ports: container.Ports.map(port => ({
          publicPort: port.PublicPort,
          privatePort: port.PrivatePort
        }))
      };
    }))
    .then(containers => {
      res.send(containers);
    })
    .catch(e => console.error(e));
});

app.use("/assets", express.static("assets"));

app.get("/remove/:container_id", (req, res) => {
  dockerode.getContainer(req.params.container_id).remove().then(() => {
    res.send("OK");
  }).catch(e => console.log(e));
});

app.get("/start/:container_id", (req, res) => {
  dockerode.getContainer(req.params.container_id).start().then(() => {
    res.send("OK");
  }).catch(e => console.log(e));
});

app.post("/create", (req, res) => {
  console.warn("body is", req.body);
  dockerode.createContainer({
      Image: req.body.image,
      AttachStdout: false,
      AttachStderr: false,
      Cmd: req.body.command || null
    })
    .then(container => {
      return container.start()
    })
    .catch(e => console.error(e));
});

app.get("/stop/:container_id", (req, res) => {
  dockerode
    .getContainer(req.params.container_id)
    .stop()
    .then(() => {
      res.send("OK");
    })
    .catch(e => {
      console.log(e);
      res.status(500).send(e);
    });
});

app.get("/kill/:container_id", (req, res) => {
  dockerode
    .getContainer(req.params.container_id)
    .remove()
    .then(() => res.send('OK'))
    .catch(e => {
      console.error(e);
      res.status(500).send(e)
    });
});

const port = 9000;
server.listen(port, () => {
  console.log(`Started node-server on ${port}`);
});
