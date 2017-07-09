CREATE TABLE IF NOT EXISTS foobar (
  id serial primary key,
  message varchar(40) NOT NULL,
  created timestamp without time zone default (now() at time zone 'utc')
);
