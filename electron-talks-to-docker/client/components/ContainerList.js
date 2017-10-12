import React from "react";
import ReactDOM from "react-dom";

import Container from "./container";

const styles = {
  empty: {},
  section: {
    padding: "1rem 1rem 1rem 0rem"
  }
};

export default class Containers extends React.Component {
  constructor(props) {
    super(props);
    this.state = { running: [], other: [] };
  }

  refresh() {
    fetch("http://localhost:9000/")
      .then(response => response.json())
      .then(containers => {
        const running = containers.filter(c => c.state === "running");
        const other = containers.filter(c => c.state !== "running");
        this.setState({
          running,
          other
        });
      })
      .catch(e => console.error(e));
  }

  componentDidMount() {
    this.refresh();
    this.refreshID = setInterval(() => this.refresh(), 3000);
  }

  componentWillUnmount() {
    clearInterval(this.refreshID);
  }

  render() {
    const { running, other } = this.state;
    return (
      <div>
        <div style={styles.section}>
          <h3>Active</h3>
          <div>
            {running.length === 0 ? (
              <span>No running containers.</span>
            ) : (
              running.map(container => (
                <Container key={container.container_id} container={container} />
              ))
            )}
          </div>
        </div>
        <div style={styles.section}>
          <h3>Inactive</h3>
          <div>
            {other.map(container => (
              <Container key={container.container_id} container={container} />
            ))}
          </div>
        </div>
      </div>
    );
  }
}
