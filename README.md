# Wildlife Tracker
#### An application that allows Rangers to track wildlife sightings.
#### By **Silas Silikhe**
## Description
An application that allows Rangers to track wildlife sightings(Both Endangered and other species) in Douglas Fir. Forest
## Setup/Installation Requirements
* git clone https://github.com/silikhe/Wildlife.git
* cd your/path/to/Wildlife-Tracker
* open with Intellij or editor of choice eg. Eclipse
* Launch Postgres via terminal with the command: psql

##### DATABASE SETUP
* CREATE DATABASE wildlife_tracker;
* \c wildlife_tracker
* CREATE TABLE animals (id serial PRIMARY KEY, name text, danger text, health text, age text, location text);
* CREATE TABLE animals (id serial PRIMARY KEY, animal id int, location text, rangername text, lastsighting timestamp);

##### TEST DATABASE SETUP
* CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;

Make sure you have gradle, spark and PostgreSQL installed.
## Known Bugs
There are no known bugs at the moment
## Technologies Used
Java, Spark, Handlebars, PostgreSQL Bootstrap, CSS
## Support and contact details
silikhesilas@gmail.com
### License
*[MIT](license.txt)*
Copyright (c) 2021 **Silikhe Silas**