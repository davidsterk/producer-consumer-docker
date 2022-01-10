# Consumer-Docker

The Consumer-docker program runs within a docker container. Build information is contained within the Dockerfile.

The program reads an input file (defined in input/input.txt) and creates json messages that are inserted into a rabbitmq
queue running in its own docker container. Before reading the file and inserting into the queue, it waits for a
connection with the RabbitMQ host. After creating and inserting the json messages into the queue, the program and docker
container terminates.