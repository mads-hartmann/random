import React from 'react'

import Terminal from '../components/terminal'


const Logo = () =>
  <div style={{
    fontWeight: 'bold',
    fontSize: '2rem',
    textAlign: 'center'
  }}>
    <h1>new</h1>
  </div>

const Header = () =>
  <div style={{
    color: 'black',
    padding: '0 1em',
  }}>
    <Logo />
  </div>


const Container = ({children}) =>
  <div style={{
    paddingLeft: '1rem',
    paddingRight: '1rem',
    margin: '0px auto'
  }}>
    {children}
  </div>

const Body = () =>
  <div>
    <p style={{
      fontSize: '1.5em',
      textAlign: 'center'
    }}>
      A lightweight command-line tool for creating new things. What kind of things? <br /><br />
      Anything that lives in file.
    </p>
    <Terminal>
      <kbd>brew install new</kbd>
      <kbd>new new</kbd>
      <pre>
        react@0.13.3 node_modules/react {'\n'}
        └── envify@3.4.0 (through@2.3.7, jstransform@10.1.0)
      </pre>
    </Terminal>
  </div>

export default () =>
  <div>
    <Container>
      <Header />
      <Body />
    </Container>
  </div>
