# FROM eclipse-temurin:17-jdk-alpine

# ARM processors
FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]