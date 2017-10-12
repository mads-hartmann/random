import React from "react";

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
    this.state = {};
    this.state = this.updateStateFromProps(props);
  }

  componentWillReceiveProps(nextProps) {
    this.setState(this.updateStateFromProps(nextProps));
  }

  render() {
    const { container, onHide, hidden } = this.state;
    if (hidden) {
      return null;
    }
    return (
      <div style={styles.container} onClick={e => onHide()}>
        <div style={styles.content} onClick={e => e.stopPropagation()}>
          <h1>{container.name}</h1>
        </div>
      </div>
    );
  }
}
