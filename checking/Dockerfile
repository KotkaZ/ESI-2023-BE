FROM maven:3.8.1-openjdk-17-slim AS builder
WORKDIR /app
COPY pom.xml ./
COPY src ./src

RUN mvn clean install package

FROM eclipse-temurin:17-jdk-alpine
# ARM processors
# FROM --platform=linux/arm64/v8 eclipse-temurin:17-jdk-focal

WORKDIR /

COPY --from=builder /app/target/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]