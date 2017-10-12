import React from "react";

import colors from "../constants/colors";
import ContainerModal from "./ContainerModal";

const color = state => {
  switch (state) {
    case "exited":
      return "grey";
    case "running":
      return "#52c352";
    default:
      return "white";
  }
};

const styles = {
  button: {
    background: "none",
    border: "none"
  },
  img: {
    width: "20px",
    height: "20px",
    verticalAlign: "middle",
    marginRight: ".5em"
  },
  loader: {
    width: "20px",
    height: "20px",
    verticalAlign: "middle",
    marginRight: ".5em"
  },
  container: {
    name: {
      display: "block",
      fontWeight: "bold"
    },
    image: {
      display: "block",
      lineHeight: "10px",
      fontSize: "12px"
    },
    status: {
      paddingTop: "5px",
      display: "block"
    }
  }
};

export default class Container extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      container: props.container,
      loading: false,
      showModal: false
    };
  }

  componentWillReceiveProps(nextProps) {
    if (this.state.container.state !== nextProps.container.state) {
      this.setState({
        ...this.state,
        container: nextProps.container,
        loading: false
      });
    }
  }

  start() {
    this.setState({
      ...this.state,
      loading: true
    });
    fetch(
      `http://localhost:9000/start/${this.state.container.container_id}`
    ).catch(e => console.error(e));
  }

  stop() {
    this.setState({
      ...this.state,
      loading: true
    });
    fetch(
      `http://localhost:9000/stop/${this.state.container.container_id}`
    ).catch(e => console.error(e));
  }

  kill() {
    this.setState({
      ...this.state,
      loading: true
    });
    fetch(
      `http://localhost:9000/kill/${this.state.container.container_id}`
    ).catch(e => console.error(e));
  }

  toggleModal() {
    console.log("toggling");
    this.setState({
      ...this.state,
      showModal: !this.state.showModal
    });
  }

  render() {
    const { container, loading, showModal } = this.state;

    const canStart =
      !loading && (container.state == "exited" || container.state == "created");
    const canPause = !loading && container.state == "running";
    const canDelete = !loading;

    return (
      <div
        style={{
          border: colors.border,
          borderLeft: `5px solid ${color(container.state)}`,
          margin: "2px",
          padding: "10px",
          display: "flex",
          justifyContent: "space-between"
        }}
      >
        <div>
          <span style={styles.container.name}>{container.name}</span>
          <span style={styles.container.image}>{container.image}</span>
          <small style={styles.container.status}>{container.status}</small>
        </div>
        <div>
          {loading ? (
            <img
              src="http://localhost:9000/assets/feathericons/refresh-cw.svg"
              className="rotating"
              style={styles.loader}
            />
          ) : null}
          {!loading ? (
            <button style={styles.button} onClick={() => this.toggleModal()}>
              <img
                src="http://localhost:9000/assets/feathericons/activity.svg"
                style={styles.img}
              />
            </button>
          ) : null}
          {canDelete ? (
            <button style={styles.button} onClick={() => this.kill()}>
              <img
                src="http://localhost:9000/assets/feathericons/minus-circle.svg"
                style={styles.img}
              />
            </button>
          ) : null}
          {canPause ? (
            <button style={styles.button} onClick={() => this.stop()}>
              <img
                src="http://localhost:9000/assets/feathericons/pause-circle.svg"
                style={styles.img}
              />
            </button>
          ) : null}
          {canStart ? (
            <button style={styles.button} onClick={() => this.start()}>
              <img
                src="http://localhost:9000/assets/feathericons/play-circle.svg"
                style={styles.img}
              />
            </button>
          ) : null}
        </div>
        <ContainerModal
          hidden={!showModal}
          container={container}
          onHide={() => this.toggleModal()}
        />
      </div>
    );
  }
}
