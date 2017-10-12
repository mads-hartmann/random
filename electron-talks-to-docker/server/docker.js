// This uses the HTTP API provided by Docker engine.
// https://docs.docker.com/engine/api/v1.31/

const http = require("http");
const querystring = require("querystring");

/**
 * Perform a request over a unix socket to the Docker daemon.
 */
function request(path, method, body) {
  const payload = JSON.stringify(body || {});
  const options = {
    method: method ? method : "GET",
    protocol: "http:",
    socketPath: "/var/run/docker.sock",
    path: path,
    headers: {
      "Content-Type": "application/json",
      "Content-Length": Buffer.byteLength(payload)
    }
  };
  return new Promise((resolve, reject) => {
    const req = http.request(options, res => {
      res.setEncoding("utf8");
      let rawData = "";
      res.on("data", chunk => (rawData += chunk));
      res.on("end", () => {
        try {
          if (rawData !== "") {
            let parsedData = JSON.parse(rawData);
            resolve(parsedData);
          } else {
            resolve();
          }
        } catch (e) {
          reject(e.message);
        }
      });
    });
    req.end(payload);
  });
}

/**
 * Start a container
 */
function start(containerId) {
  return request(`/containers/${containerId}/start`, "POST");
}

/**
 * List all existing containers.
 */
function containers() {
  return request("/containers/json?all=1").then(response => {
    return response.map(container => {
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
    });
  });
}

module.exports = {
  containers,
  request,
  start
};
