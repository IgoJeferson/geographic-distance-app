# Geographic Distance

Rest service that return the geographic (straignt line) distance between two postal codes in the UK.

To calculate the distance between the two points, the algorithm used is Haversine formula.

## Prerequisites

- Java8
- Docker
- Docker-compose
- Lombok Plugin
- Git Large File Storage [git-lfs](https://git-lfs.github.com/)

## Used technologies

- Java
- Flyway
- MySQL
- Lombok
- SpringBoot + Web + Actuator 

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

```./mvnw test ```


## Run to test the API

* To list postcodes

```curl --location --request GET 'localhost:8080/rest/postcodes?pageNumber=1&pageSize=25'```

```json
{
  "content": [
    {
      "id": 6,
      "postcode": "AB11 8RQ",
      "latitude": "57.135968",
      "longitude": "-2.072115"
    },
    {
      "id": 7,
      "postcode": "AB12 3FJ",
      "latitude": "57.097987",
      "longitude": "-2.077447"
    },
    {
      "id": 8,
      "postcode": "AB12 4NA",
      "latitude": "57.064273",
      "longitude": "-2.130018"
    },
    {
      "id": 9,
      "postcode": "AB12 5GL",
      "latitude": "57.081938",
      "longitude": "-2.246567"
    },
    {
      "id": 10,
      "postcode": "AB12 9SP",
      "latitude": "57.148707",
      "longitude": "-2.097806"
    }
  ],
  "pageNumber": 1,
  "pageSize": 5,
  "totalElements": 1785253,
  "totalPages": 357051,
  "last": false
}
```

* To Calculate the distance between two postcodes

```curl --location --request GET 'localhost:8080/rest/geographic-distance?origin=AB24 2TF&destination=AB21 7XB'```

```json
{
    "origin": {
        "id": 1056,
        "postcode": "AB24 2TF",
        "latitude": "57° 10' 9\" N",
        "longitude": "2° 6' 32\" W"
    },
    "destination": {
        "id": 67,
        "postcode": "AB21 7XB",
        "latitude": "57° 14' 15\" N",
        "longitude": "2° 9' 42\" W"
    },
    "distance": 8.22212107863058,
    "unit": "km"
}
```


### Stop

```docker-compose down```

```docker-compose down --rmi all```


## Note about the load of the database UK Post codes

Considering the size of the script is 193MB, it was not possible to commit on Github, if you want to do full test, 
you should download the script from https://www.freemaptools.com/download-uk-postcode-lat-lng.htm 
and moved to the directory ```src/main/resources/db/migration``` with name like:  V1.1__insert_ukpostcodes.sql


## Improvements
- Unit tests!
  - Increase the coverage doing more unit tests in the service and in the controller layer
- Authentication - restrict the service to those who know a username/password combination