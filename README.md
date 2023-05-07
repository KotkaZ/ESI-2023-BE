# Online Hotel Management 
This Spring Boot project focuses on developing an online hotel management system while exploring key concepts such as microservices, load balancing, enterprise system integration, Eureka discovery, and Kafka messaging.
This is an example project for building scalable and resilient applications using Spring Boot and related technologies.

## Project Overview
The online hotel management system aims to provide a comprehensive platform for managing hotel operations, reservations, room availability, and customer bookings. The project demonstrates the use of microservices architecture, load balancing techniques, and enterprise system integration to ensure a robust and efficient system.

## Key Features
* <b>Microservices Architecture:</b> The project is structured as a set of microservices, each responsible for a specific functionality, allowing for independent development, deployment, and scaling of services.
* <b>Load Balancing:</b> Load balancing is implemented to evenly distribute incoming requests across multiple instances of microservices, optimizing resource utilization and improving system performance.
* <b>Enterprise System Integration:</b> The system showcases integration with enterprise systems using Spring Boot, including Apache Kafka for reliable messaging and communication between different microservices.
* <b>Eureka Discovery:</b> Eureka is used for service discovery and registration, enabling microservices to locate and communicate with each other seamlessly.
* <b>User Management:</b> The system provides functionality for user registration, authentication, and authorization, ensuring secure access to the application.

## Requirements
* Java 17
* Docker Engine
* Docker Compose

## Getting started

Run all required Kafka and Databases services defined in `docker-compose.yml`.
```bash
docker-compose up --build -d
```


Initialize Kafka listener for bookingTopic
```bash
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092  --topic bookingTopic --from-beginning
```

Initialize Kafka listener for paymentTopic
```bash
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092  --topic paymentTopic --from-beginning
```

Initialize Kafka listener for checkingTopic
```bash
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092  --topic checkingTopic --from-beginning
```


Run the following commands to start all the microservices:
```bash
cd discovery-server/
./mvnw spring-boot:run # Runs on 8761 by default
```
```bash
cd auth/
./mvnw spring-boot:run # Runs on 8086 by default
```
```bash
cd bookings/
./mvnw spring-boot:run # Runs on 8079 by default
```
```bash
cd checking/
./mvnw spring-boot:run # Runs on 8085 by default
```
```bash
cd notifications/
./mvnw spring-boot:run # Runs on 8082 by default
```
```bash
cd payments/
./mvnw spring-boot:run # Runs on 8083 by default
```
```bash
cd rooms/
./mvnw spring-boot:run # Runs on 8081 by default
```
```bash
cd support/
./mvnw spring-boot:run # Runs on 8084 by default
```
```bash
cd api-gateway/
./mvnw spring-boot:run # Runs on 8080 by default
```

> **_NOTE:_**  When staring services via IDE, make sure the compile lifecycle is started. It is used to generate code based on API specs.

Load balancing is enabled by default.
To have multiple instances of same service, simply start it on a different port.
E.g.
```bash
#!/bin/bash

export SERVER_PORT=8090 # Overrides the default port defined in application.properties
cd bookings/
./mvnw spring-boot:run 
unset SERVER_PORT
```

See the started microservices from Eureka UI: http://localhost:8761/

## Acknowledgements
We would like to express our gratitude to the following resources and documentation that helped in the development of this project:

* [Spring Boot Documentation](https://spring.io/projects/spring-boot)
* [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
* [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
