import React from "react";
import ReactDOM from "react-dom";
import { HashRouter as Router, Route } from "react-router-dom";

import Sidebar from "./components/Sidebar";
import ManageContainers from "./pages/ManageContainers";
import About from "./pages/About";

const Application = () => {
  return (
    <Router>
      <div
        style={{
          display: "flex",
          width: "100%",
          height: "100%"
        }}
      >
        <Sidebar />
        <main style={{ flexGrow: 1, overflowY: "scroll" }}>
          <Route path="/about" component={About} />
          <Route exact path="/" component={ManageContainers} />
        </main>
      </div>
    </Router>
  );
};

ReactDOM.render(<Application />, document.getElementById("root"));
