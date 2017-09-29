import React from 'react'
import ReactDOM from 'react-dom'

import Section from '../layout/Section'

export default () => {
  return (
    <div>
      <Section>
      <h1>
        <img src="http://localhost:9000/assets/feathericons/heart.svg" style={{
          width: '32px',
          height: '32px',
          verticalAlign: 'middle',
          marginRight: '.5em'
        }}/>
        About
      </h1>
      <p>
        This is a prototype of an Electron application that talks to Docker. It
        was made as a prototype to explore if we wanted to go in this direction
        for our internal developer setup at Famly.
      </p>
      </Section>
    </div>
  )
}
