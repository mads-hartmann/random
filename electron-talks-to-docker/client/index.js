import React from 'react'
import ReactDOM from 'react-dom'

class Containers extends React.Component {

  constructor(props) {
    super(props)
    this.state = {containers: []}
  }

  componentDidMount() {
    fetch('http://localhost:9000/')
      .then((response) => response.json())
      .then(containers => {
        console.log('what', this)
        this.setState({
          containers
        })
      })
      .catch(e => console.error(e))
  }

  render() {
    return (
      <ul>
        {this.state.containers.map(container => (
          <li key={container.container_id}>{container.name}</li>
        ))}
      </ul>
    )
  }
}

ReactDOM.render(
  <Containers />,
  document.getElementById('root')
);
