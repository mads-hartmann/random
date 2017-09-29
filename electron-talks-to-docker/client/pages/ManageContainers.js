import React from 'react';
import ReactDOM from 'react-dom';

import ContainerList from '../ContainerList';
import Section from '../layout/Section';

export default () => {
  return (
    <div>
      <Section>
        <h1>
          <img
            src="http://localhost:9000/assets/feathericons/server.svg"
            style={{
              width: '32px',
              height: '32px',
              verticalAlign: 'middle',
              marginRight: '.5em'
            }}
          />
          Manage Containers
        </h1>
        <p>
          This section gives you an overview of your current services, both
          running and otherwise.
        </p>
      </Section>
      <Section>
        <ContainerList />
      </Section>
    </div>
  );
};
