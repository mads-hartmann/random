import React from "react";

import Logs from "./Logs";

const styles = {
  container: {
    width: "100%",
    height: "100%",
    position: "fixed",
    top: "0",
    left: "0",
    background: "rgba(0,0,0,0.7)"
  },
  content: {
    margin: "2rem auto",
    padding: "2rem",
    maxWidth: "600px",
    background: "white"
  }
};

export default class ContainerModal extends React.Component {
  updateStateFromProps(props) {
    return {
      ...this.state,
      hidden: props.hidden,
      onHide: props.onHide,
      container: props.container
    };
  }

  constructor(props) {
    super(props);
    this.state = {
      logs: [],
      streaming: true
    };
    this.state = this.updateStateFromProps(props);
  }

  openSocket() {
    // Create WebSocket connection.
    const url = `ws://localhost:9000/logs?containerId=${this.state.container
      .container_id}`;
    console.log("Starting socket", url);
    const socket = new WebSocket(url);

    // Connection opened
    socket.addEventListener("open", event => {
      socket.send("Hello Server!");
    });

    // Listen for messages
    socket.addEventListener("message", event => {
      console.log("Message from server ", event.data);
      this.setState((prevState, props) => ({
        ...prevState,
        logs: prevState.logs.concat([event.data])
      }));
    });
  }

  closeSocket() {
    this.setState((prevState, props) => ({
      ...prevState,
      streaming: false
    }));
  }

  componentWillReceiveProps(nextProps) {
    this.setState(this.updateStateFromProps(nextProps));

    if (this.state.hidden !== nextProps.hidden) {
      if (nextProps.hidden) {
        this.closeSocket();
      } else {
        this.openSocket();
      }
    }
  }

  render() {
    const { container, onHide, hidden, logs, streaming } = this.state;
    if (hidden) {
      return null;
    }
    return (
      <div style={styles.container} onClick={e => onHide()}>
        <div style={styles.content} onClick={e => e.stopPropagation()}>
          <h1>{container.name}</h1>
          <h2>Logs</h2>
          {streaming ? <span>Streaming</span> : null}
          <Logs lines={logs} />
        </div>
      </div>
    );
  }
}
