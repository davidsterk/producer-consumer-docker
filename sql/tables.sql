USE smartwatch;

CREATE TABLE IF NOT EXISTS activfit
(sensorname varchar(50), starttime varchar(50), endtime varchar(50), activity varchar(50), duration varchar(50));

CREATE TABLE IF NOT EXISTS activity
(sensorname varchar(50), timestamp varchar(50), time_stamp varchar(50), stepcounts varchar(50), stepdelta varchar(50));

CREATE TABLE IF NOT EXISTS batterysensor
(sensorname varchar(50), timestamp varchar(50), percent varchar(50), charging varchar(50));

CREATE TABLE IF NOT EXISTS bluetooth
(sensorname varchar(50), timestamp varchar(50), state varchar(50));

CREATE TABLE IF NOT EXISTS heartrate
(sensorname varchar(50), timestamp varchar(50), bpm varchar(50));

CREATE TABLE IF NOT EXISTS lightsensor
(sensorname varchar(50), timestamp varchar(50), lux varchar(50));
