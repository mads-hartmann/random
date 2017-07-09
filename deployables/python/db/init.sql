CREATE DATABASE deployables
OWNER postgres
ENCODING 'UTF8';

\connect deployables

CREATE TABLE migrations (
  id serial primary key,
  created timestamp without time zone default (now() at time zone 'utc'),
  filename varchar(120) NOT NULL UNIQUE,
  checksum varchar(50) NOT NULL UNIQUE
);
