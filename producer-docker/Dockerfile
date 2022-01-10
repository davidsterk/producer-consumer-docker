## Build Stage ##
FROM maven:3.8.4-jdk-11 AS mvn_build
WORKDIR /app
COPY pom.xml .
COPY src src/
RUN mvn clean package


## Run Stage ##
FROM openjdk:11
WORKDIR /app
COPY input input/
ENV RABBITMQ_HOST=rabbitmq-smartwatch
COPY --from=mvn_build /app/target/producer-docker-1.0.jar .
ENTRYPOINT ["java", "-jar", "producer-docker-1.0.jar"]

