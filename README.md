# Employee
The practical application for BellIntegrator

## Technology stack

* Spring Boot 2.5.6
* Java 11
* JPA/Hibernate
* PostgreSQL in memory database
* Lombok
* Swagger
* Orika (mapping)

## What was implemented
* To get information use Swagger UI: http://localhost:8888/swagger-ui.html

## Repository
* https://github.com/ArsenyPochinka/Employee

## Pre-installation
* Create a database in PostgreSQL and enter its coordinates and input parameters in "application.properties" by path: src/main/resources/application.properties
* Create tables by running the file "schema.sql" (src/main/resources/schema.sql) in the database console
* Fill tables with data by running the file "data.sql" (src/main/resources/data.sql) in the database console

## Build
```cmd
mvn clean install
```
