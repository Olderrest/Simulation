DROP TABLE route_stop;
DROP TABLE buses;
DROP TABLE routes;
DROP TABLE stops;
DROP TABLE bus_info;
DROP TABLE simulation_work_time;
-----------------------
--table routes
-----------------------
CREATE TABLE routes
(
  id                SERIAL PRIMARY KEY,
  stops_number      INTEGER NOT NULL,
  bus_number        INTEGER NOT NULL,
  move_bus_interval INTEGER NOT NULL,
  route_type        VARCHAR NOT NULL,
  avg_workload      BIGINT  NOT NULL
);

-----------------------
--table stops
-----------------------
CREATE TABLE stops
(
  id           SERIAL PRIMARY KEY,
  time         INTEGER NOT NULL,
  avg_workload BIGINT  NOT NULL
);

-----------------------
--table route_stop
-----------------------
CREATE TABLE route_stop
(
  id      SERIAL PRIMARY KEY,
  routeId INTEGER NOT NULL REFERENCES routes (id) ON DELETE CASCADE ON UPDATE RESTRICT,
  stop_id INTEGER NOT NULL REFERENCES stops (id) ON DELETE CASCADE ON UPDATE RESTRICT
);

-----------------------
--table buses
-----------------------
CREATE TABLE buses
(
  id           SERIAL PRIMARY KEY,
  size         INTEGER NOT NULL,
  load_percent FLOAT   NOT NULL,
  route_id     INTEGER NOT NULL REFERENCES routes (id) ON DELETE CASCADE ON UPDATE RESTRICT
);

-----------------------
--table bus_info
-----------------------
CREATE TABLE bus_info
(
  id                SERIAL PRIMARY KEY,
  year              INTEGER NOT NULL,
  month             INTEGER NOT NULL,
  day               INTEGER NOT NULL,
  hour              INTEGER NOT NULL,
  minute            INTEGER NOT NULL,
  stop_id           INTEGER NOT NULL,
  routeId           INTEGER NOT NULL,
  bus_id            INTEGER NOT NULL,
  passengers_in     INTEGER NOT NULL,
  passengers_out    INTEGER NOT NULL,
  passengers_in_bus INTEGER NOT NULL
);


CREATE TABLE simulation_work_time
(
  id          SERIAL PRIMARY KEY,
  years       INTEGER     NOT NULL,
  months      INTEGER     NOT NULL,
  days        INTEGER     NOT NULL,
  start_time  VARCHAR(50) NOT NULL,
  end_time    VARCHAR(50) NOT NULL,
  route_count INTEGER     NOT NULL,
  bus_count   INTEGER     NOT NULL,
  stop_count  INTEGER     NOT NULL
);
-----------------------
--table users
-----------------------
CREATE TABLE users
(
  id         serial PRIMARY KEY,
  login      VARCHAR(20) NOT NULL UNIQUE,
  password   VARCHAR(20) NOT NULL,
  first_name VARCHAR(20) NOT NULL,
  last_name  VARCHAR(20) NOT NULL
);



