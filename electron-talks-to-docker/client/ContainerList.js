import React from 'react'
import ReactDOM from 'react-dom'

import Container from './container'

const intOfState = (status) => {
  console.log(status)
  switch (status) {
    case "running":
      return 3
    default:
      return -1
  }
}

export default class Containers extends React.Component {

  constructor(props) {
    super(props)
    this.state = {containers: []}
  }

  refresh() {
    fetch('http://localhost:9000/')
      .then((response) => response.json())
      .then(containers => {
        const sorted = containers.sort((c1, c2) => {
          return intOfState(c1.state) <= intOfState(c2.state)
        })
        this.setState({
          containers: sorted
        })
      })
      .catch(e => console.error(e))
  }

  componentDidMount() {
    this.refresh()
    this.refreshID = setInterval(
      () => this.refresh(),
      3000
    );
  }

  componentWillUnmount() {
    clearInterval(this.refreshID);
  }

  render() {
    return (
      <div>
        {this.state.containers.map(container => (
          <Container
            key={container.container_id}
            container={container}
          />
        ))}
      </div>
    )
  }
}
