import React from 'react'

const WindowButton = ({theme}) => {

  const themeStyles = {
    'close': {
      background: '#C85252'
    },
    'minimize': {
      background: '#DDB522'
    },
    'zoom': {
      background: '#1ABF52'
    }
  }[theme]

  return <div style={{
    margin: '0px',
    marginRight: '4px',
    width: '10px',
    height: '10px',
    borderRadius: '5px',
    ...themeStyles
  }}></div>
}

const Output = ({lines}) =>
  <div style={{
    fontFamily: 'Source Code Pro',
    background: 'black',
    color: 'white',
    padding: '10px'
  }}>
    {lines}
  </div>

export default ({
  children
}) => {

  var lines = children.map((line, key) => {
    if(line.type === 'kbd') {
      return (
        <div key={key} className="terminal-line">
          <span className="prompt">&gt; </span>
          <kbd>{line}</kbd>
        </div>
      );
    }
    else if (line.type === 'pre') {
      return (
        <div key={key} className="terminal-line">
          <pre>{line}</pre>
        </div>
      );
    }
  });

  return (
    <div style={{
      margin: '0 auto',
      borderRadius: '3px',
      maxWidth: '500px',
      background: 'rgba(0, 0, 0, 0)'
    }}>
      <header style={{
        padding: '8px',
        display: 'flex',
        borderRadius: '3px 3px 0px 0px',
        background: 'linear-gradient(-180deg, #EAEAEA 0%, #D2D2D2 100%)'
      }}>
        <WindowButton theme="close" />
        <WindowButton theme="minimize" />
        <WindowButton theme="zoom" />
      </header>
      <Output lines={lines} />
    </div>
  );
}
