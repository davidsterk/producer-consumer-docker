# Producer-Consumer-Docker

This project is a microservice base implementation of the Producer-Consumer-DBInsert project using Docker containers. The project contains
two different executables to manage the producer and consumer functions. Messages are passed from the producer to 
consumer using RabbitMQ as a message broker. The consumer inserts messages into a mysql database. The producer,
consumers, rabbitmq host, and mysql host run in their own docker containers.

## Producer-Consumer
The producer program reads an input file containing smartwatch sensor data in json format. For each record, it generates a
message that is inserted into the rabbitmq queue. After completion, the program and docker container terminates.

## Consumer-Producer
The consumer program listens and takes messages from the rabbitmq queue. Based on the sensor type, it creates the
appropriate data access object model which is used to insert into the mysql database.

## Build
The project is completely self-contained and can be run with Docker Compose using the docker-compose.yml file. Open a
terminal and navigate to this project's root directory: producer-consumer-docker

To build the project with one consumer, run:

```
docker-compose up
```

To run multiple consumers use the --scale flag on consumer-docker: --scale consumer-docker=#
where # is the number of consumer-docker instances.

```
docker-compose up --scale consumer-docker=3
```

