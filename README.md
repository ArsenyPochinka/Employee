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
1. Create a database in PostgreSQL and enter its coordinates and input parameters in "application.properties" by path: src/main/resources/application.properties
2. First option: 
* Create tables by running the file "schema.sql" (src/main/resources/schema.sql) in the database console
* Fill tables with data by running the file "data.sql" (src/main/resources/data.sql) in the database console
2. Second option:
* Remove the commit in the "application.properties" (src/main/resources/application.properties) before launching the program in the line "#spring.datasource.initialization-mode=always"
* After the first launch of the program, put the comment back so that the data in the table is not duplicated

## Build
```cmd
mvn clean install
```
