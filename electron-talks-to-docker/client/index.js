import React from 'react'
import ReactDOM from 'react-dom'

import Sidebar from './sidebar'
import ManageServices from './ManageContainers'

const Application = () => {
  return (
    <div style={{
      display: 'flex',
      width: '100%',
      height: '100%'
    }}>
      <Sidebar />
      <main style={{'flexGrow': 1}}>
        <ManageServices />
      </main>
    </div>
  )
}

ReactDOM.render(
  <Application />,
  document.getElementById('root')
);
