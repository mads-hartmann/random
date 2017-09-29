import React from 'react';

export default () => {
  const styles = {
    flexGrow: 0,
    width: '220px',
    minWidth: '220px',
    overflowX: 'hidden',
    overflowY: 'auto',
    color: 'hsl(0,0%,44%)',
    borderRight: '1px solid hsl(0,0%,88%)',
    backgroundColor: 'hsl(0,0%,96%)'
  };
  const navHeaderStyles = {
    position: 'relative',
    padding: '2rem',
    marginBottom: '1rem',
    borderBottom: '1px solid hsl(0,0%,88%)'
  }
  return (
    <nav style={styles}>
      <header style={navHeaderStyles}>
        <h1
          style={{
            textTransform: 'uppercase',
            fontWeight: '300',
            lineHeight: '1',
            margin: '0'
          }}
        >
          Dev <strong>Dashboard</strong>
        </h1>
      </header>
      <ul style={{
        listStyle: 'none',
        margin: '0 0 0 5px',
        padding: '0 2em',
        textTransform: 'uppercase',
        fontSize: '11px',
        lineHeight: '24px'
      }}>
        <li>
          <img src="http://localhost:9000/assets/feathericons/heart.svg" style={{
            width: '16px',
            height: '16px',
            verticalAlign: 'middle',
            marginRight: '.5em'
          }}/>
          About
        </li>
        <li>
          <img src="http://localhost:9000/assets/feathericons/server.svg" style={{
            width: '16px',
            height: '16px',
            verticalAlign: 'middle',
            marginRight: '.5em'
          }}/>
          Manage Containers
        </li>
      </ul>
    </nav>
  );
};
