import React from 'react'

import colors from './colors'

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

export default class Container extends React.Component {
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
        border: colors.border,
        borderLeft: `5px solid ${color(container.state)}`,
        margin: '2px',
        padding: '10px',
        display: 'flex',
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
