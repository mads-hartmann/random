const http = require('http');

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
    }
    return new Promise((resolve, reject) => {
        http.get(req, (res) => {
            res.setEncoding('utf8');
            let rawData = '';
            res.on('data', (chunk) => rawData += chunk);
            res.on('end', () => {
                try {
                    if (rawData !== '') {
                        let parsedData = JSON.parse(rawData);
                        resolve(parsedData);
                    } else {
                        resolve()
                    }
                } catch (e) {
                    reject(e.message);
                }
            });
        })
    });
}

/**
 * List all existing containers.
 */
function containers() {
    return request('/containers/json?all=1')
        .then((response) => {
            return response.map(container => {
                const name = container.Names.join(" ").replace("/", "");
                return {
                    name: name,
                    container_id: container.Id,
                    state: container.State,
                    ports: container.Ports.map(port => ({
                        publicPort: port.PublicPort,
                        privatePort: port.PrivatePort
                    }))
                }
            });
        })
}

module.exports = {
    containers,
    request
};
