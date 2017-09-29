const http = require('http');

// This uses the HTTP API provided by Docker engine.
// https://docs.docker.com/engine/api/v1.31/

/**
 * Perform a request over a unix to the Docker daemon.
 */
function request(path, method) {
  var method = method ? method : 'GET';
  const req = {
    method: method,
    protocol: 'http:',
    socketPath: '/var/run/docker.sock',
    path: path
  };
  return new Promise((resolve, reject) => {
    http.get(req, res => {
      res.setEncoding('utf8');
      let rawData = '';
      res.on('data', chunk => (rawData += chunk));
      res.on('end', () => {
        try {
          if (rawData !== '') {
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
  });
}

/**
 * List all existing containers.
 */
function containers() {
  return request('/containers/json?all=1').then(response => {
    return response.map(container => {
      const name = container.Names.join(' ').replace('/', '');
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
  request
};
