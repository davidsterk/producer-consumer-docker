## Build Stage ##
FROM maven:3.8.2-jdk-11 AS mvn_build
WORKDIR /app
COPY pom.xml .
COPY src src/
RUN mvn clean package


## Run Stage ##
FROM openjdk:11
WORKDIR /app
ENV RABBITMQ_HOST=rabbitmq
ENV MYSQL_USER=root
ENV MYSQL_USER_PASSWORD=smartwatch
ENV MYSQL_DATABASE=smartwatch
ENV MYSQL_DRIVER=org.mariadb.jdbc.Driver
ENV MYSQL_HOST=mysql-smartwatch
ENV MYSQL_PORT=3306
COPY --from=mvn_build /app/target/consumer-docker-1.0.jar .
ENTRYPOINT ["java", "-jar", "consumer-docker-1.0.jar"]
