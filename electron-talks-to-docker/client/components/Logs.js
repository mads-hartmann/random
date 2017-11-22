import React from "react";

const styles = {
  container: {
    padding: "5px",
    color: "white",
    background: "black",
    fontSize: "10px",
    maxHeight: "300px",
    overflowY: "scroll"
  },
  line: {
    display: "block"
  }
};

export default ({ lines }) => {
  return (
    <div style={styles.container}>
      {lines.map(line => <span style={styles.line}>{line}</span>)}
    </div>
  );
};
