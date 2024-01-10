# Robot Management System

## Description
RobotManagementSystem is a REST API that allows the clients to communicate with robots. **The specific communication with robots is outside the scope of the API.**

The system provides the following functionalitites :

- Registering a robot.
- Registering a medication.
- Loading a robot with medication items.
- Checking loaded medication items for a given robot.
- Checking available robots for loading.
- Check robot battery level for a given robot.
- Periodic logging of the battery levels of robots.

## Run the project locally
RobotManagementSystem is a Spring Boot application built using Maven. You can build a jar file and run it from the command line (it should work with Java 17 or newer) : 
```
  git clone https://github.com/abdelrahmankhaledg/RobotManagementSystem.git
  cd RobotManagementSystem
  ./mvnw package
  java -jar target/robot-0.0.1-SNAPSHOT.jar
  
```
You can access the swagger API documentation at http://localhost:8080/swagger-ui/index.html. You can also find the postman collection for the API under src/main/resources/Robot.postman_collection.json

## Database Configuration
In its default configuration, RobotManagementSystem uses an in-memory database (H2) which gets populated at startup with data. The h2 console is exposed at http://localhost:8080/h2-console, and it is possible to inspect the content of the database using the jdbc:h2:mem:robotdb URL with username "sa" and blank password.