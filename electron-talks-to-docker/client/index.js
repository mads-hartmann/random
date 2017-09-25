import React from 'react'
import ReactDOM from 'react-dom'

const color = (state) => {
  switch (state) {
    case "exited":
      return "grey";
    case "running":
      return "green"
    default:
      return "white"
    }
}

class Container extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      container: props.container,
      loading: false
    }
  }

  componentWillReceiveProps(nextProps) {
    if (this.state.container.state !== nextProps.container.state) {
      this.setState({
        container: nextProps.container,
        loading: false
      })
    }
  }

  start(container_id) {
    this.setState({
      ...this.state,
      loading: true
    })
    fetch(`http://localhost:9000/start/${this.state.container.container_id}`)
      .catch(e => console.error(e))
  }

  stop(container_id) {
    this.setState({
      ...this.state,
      loading: true
    })
    fetch(`http://localhost:9000/stop/${this.state.container.container_id}`)
      .catch(e => console.error(e))
  }

  render() {
    const {
      container,
      loading
    } = this.state;

    return (
      <div style={{
        border: '1px solid black',
        margin: '2px',
        padding: '10px',
        display: 'flex',
        background: color(container.state),
        justifyContent: 'space-between'
      }}>
        <div>
          {container.name}<br />
          <small>{loading ? 'LOADING' : container.state}</small>
        </div>
        <div>
          <button onClick={() => this.stop()}>stop</button>
          <button onClick={() => this.start()}>start</button>
        </div>
      </div>
    )
  }
}

class Containers extends React.Component {

  constructor(props) {
    super(props)
    this.state = {containers: []}
  }

  refresh() {
    fetch('http://localhost:9000/')
      .then((response) => response.json())
      .then(containers => {
        this.setState({
          containers
        })
      })
      .catch(e => console.error(e))
  }

  componentDidMount() {
    this.refresh()
    this.refreshID = setInterval(
      () => this.refresh(),
      1000
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

ReactDOM.render(
  <Containers />,
  document.getElementById('root')
);
