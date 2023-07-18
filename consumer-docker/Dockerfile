## Build Stage ##
FROM maven:3.9.0-eclipse-temurin-17 AS mvn_build
WORKDIR /app
COPY pom.xml .
COPY src src/
RUN mvn clean package


## Run Stage ##
FROM eclipse-temurin:17-jdk
WORKDIR /app
ENV RABBITMQ_HOST=rabbitmq-smartwatch
ENV SQL_USER=root
ENV SQL_USER_PASSWORD=smartwatch
ENV SQL_DATABASE=smartwatch
ENV SQL_HOST=mysql-smartwatch
ENV SQL_PORT=3306
COPY --from=mvn_build /app/target/consumer-docker-1.0.jar .
ENTRYPOINT ["java", "-jar", "consumer-docker-1.0.jar"]
