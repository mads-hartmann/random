import React from "react";

import Styles from "../constants/styles";

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
    maxWidth: "400px",
    background: "white"
  },
  form: {
    display: "flex",
    flexDirection: "column"
  },
  input: {
    margin: "5px 0px",
    padding: "5px",
    lineHeight: "20px"
  }
};

export default ({ hidden, onSubmit, onHide }) => {
  const submit = () => {
    onSubmit({
      image: document.getElementById("image").value,
      command: document.getElementById("command").value.split(",")
    });
    return false;
  };
  if (hidden) {
    return null;
  }
  return (
    <div style={styles.container} onClick={e => onHide()}>
      <div style={styles.content} onClick={e => e.stopPropagation()}>
        <h1>Start New Container</h1>
        <form style={styles.form} onSubmit={() => submit()}>
          <input
            style={styles.input}
            id="image"
            type="text"
            placeholder="Image"
          />
          <input
            style={styles.input}
            id="command"
            type="text"
            placeholder="Command"
          />
          <button style={Styles.button.green} type="submit">
            Start
          </button>
        </form>
      </div>
    </div>
  );
};
