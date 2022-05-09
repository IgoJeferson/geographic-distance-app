# Geographic Distance

Rest service that return the geographic (straignt line) distance between two postal codes in the UK.

To calculate the distance between the two points, the algorithm used is Haversine formula.

## Prerequisites

- Java8
- Docker
- Docker-compose
- Lombok Plugin

## Used technologies

- Java
- Flyway
- MySQL 

## Installation

* Download the repository as a zip or clone
* Unzip the files in a directory
* Navigate to the root directory
* run the command ```./mvnw clean install ```

## Running the Application

* To run the application locally, it will be necessary to run docker-compose, which will execute a MySQL instance and the service.
* Run in the project root:

``` docker-compose -d up ```

Note: The first execution could take a while, considering that will download the images, and the upload of 
more than 1 million  registers in the database. Could take something like 10 minutes depending on the 
available resources.

Source of the db scripts: https://www.freemaptools.com/download-uk-postcode-lat-lng.htm

## Running the Unit Tests


## Run to test the API

---- Examples


### Run app
./mvnw spring-boot:run



### Stop
docker-compose down

docker-compose down --rmi all


* Features

- Unit tests!
- Updating postal codes
- Request logging (log the two post codes in the request;
  preferably in some way so we can later aggregate and report easily);
- Authentication - restrict the service to those who know a username/password combination

## Improvements