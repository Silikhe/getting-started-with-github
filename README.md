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
* For Psql:
```
CREATE DATABASE wildlife_tracker;
CREATE TABLE sightings (id serial PRIMARY KEY, animalid int,location varchar,rangername varchar,lastseen timestamp);
CREATE TABLE animals (id serial PRIMARY KEY, name varchar,health varchar,age varchar, type varchar);
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;
```
* Run the App (gradle run))
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