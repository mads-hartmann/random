import React from 'react'

import colors from '../colors';

export default ({
  children
}) => (
  <div style={{
    position: 'relative',
    margin: '0 auto',
    padding: '2rem 2rem 1rem 2rem',
    borderBottom: `1px solid ${colors.colorBorder}`
  }}>
    {children}
  </div>
)
