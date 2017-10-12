import React from "react";
import ReactDOM from "react-dom";

import Styles from "../constants/styles";
import Section from "../layout/Section";
import NewContainerModal from "../components/NewContainerModal";
import ContainerList from "../components/ContainerList";

export default class ManageContainers extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      showModal: false
    };
  }

  toggleModal() {
    this.setState({
      ...this.state,
      showModal: !this.state.showModal
    });
  }

  createContainer(formData) {
    console.log(formData);
    this.toggleModal();
    fetch(`http://localhost:9000/create`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(formData)
    })
      .then(e => console.log(e))
      .catch(e => console.error(e));
  }

  render() {
    const { showModal } = this.state;
    return (
      <div>
        <Section>
          <h1>
            <img
              src="http://localhost:9000/assets/feathericons/server.svg"
              style={{
                width: "32px",
                height: "32px",
                verticalAlign: "middle",
                marginRight: ".5em"
              }}
            />
            Manage Containers
          </h1>
          <p>
            This section gives you an overview of your current services, both
            running and otherwise.
          </p>
          <button
            style={Styles.button.green}
            onClick={() => this.toggleModal()}
          >
            New container
          </button>
        </Section>
        <Section>
          <ContainerList />
        </Section>
        <NewContainerModal
          hidden={!showModal}
          onSubmit={formData => this.createContainer(formData)}
          onHide={() => this.toggleModal()}
        />
      </div>
    );
  }
}
