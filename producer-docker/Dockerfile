## Build Stage ##
FROM maven:3.9.6-eclipse-temurin-21 AS mvn_build
WORKDIR /app
COPY pom.xml .
COPY src src/
RUN mvn clean package


## Run Stage ##
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY input input/
ENV RABBITMQ_HOST=rabbitmq-smartwatch
COPY --from=mvn_build /app/target/producer-docker-1.0.jar .
ENTRYPOINT ["java", "-jar", "producer-docker-1.0.jar"]

