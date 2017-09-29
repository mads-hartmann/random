import React from 'react';
import {NavLink} from 'react-router-dom'

const styles = {
  nav: {
    flexGrow: 0,
    width: '220px',
    minWidth: '220px',
    overflowX: 'hidden',
    overflowY: 'auto',
    color: 'hsl(0,0%,44%)',
    borderRight: '1px solid hsl(0,0%,88%)',
    backgroundColor: 'hsl(0,0%,96%)'
  },
  header: {
    position: 'relative',
    padding: '2rem',
    marginBottom: '1rem',
    borderBottom: '1px solid hsl(0,0%,88%)'
  },
  h1: {
    textTransform: 'uppercase',
    fontWeight: '300',
    lineHeight: '1',
    margin: '0'
  },
  ul: {
    listStyle: 'none',
    margin: '0 0 0 4px',
    padding: '0 2em'
  },
  img: {
    width: '16px',
    height: '16px',
    verticalAlign: 'middle',
    marginRight: '.5em'
  },
  link: {
    style: {
      color: 'inherit',
      textTransform: 'uppercase',
      fontSize: '11px',
      lineHeight: '24px',
      textDecoration: 'none'
    },
    active: {
      fontWeight: 'bold'
    }
  }
}

export default () => {
  return (
    <nav style={styles.nav}>
      <header style={styles.header}>
        <h1 style={styles.h1}>
          Dev <strong>Dashboard</strong>
        </h1>
      </header>
      <ul style={styles.ul}>
        <li>
        <img src="http://localhost:9000/assets/feathericons/server.svg" style={styles.img} />
        <NavLink exact activeStyle={styles.link.active} style={styles.link.style} to="/">Manage Containers</NavLink>
        </li>
        <li>
          <img src="http://localhost:9000/assets/feathericons/heart.svg" style={styles.img}/>
          <NavLink activeStyle={styles.link.active} style={styles.link.style} to="/about">About</NavLink>
        </li>
      </ul>
    </nav>
  );
};
