/**
 * Consumer-Docker Main class
 * Program works as follows
 * First, program attempts to connect to the rabbitmq server
 * Second, it listens and removes messages from the queue
 * Third, based on the sensor type contained within the message, it will insert the contents into the appropriate table
 * within mysql
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.StorageService;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.time.Instant;
import java.time.Duration;

public class Main {
    
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String RABBITMQ_HOST = System.getenv("RABBITMQ_HOST");
    private static final String QUEUE_NAME = "smartwatch";
    private static Connection connection;
    private static Channel channel;
    private static Instant lastMessageTime;

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        getChannel(factory);
        logger.info("Connected to RabbitMQ Channel: "+channel.getChannelNumber());
        lastMessageTime = Instant.now();
        CountDownLatch latch = new CountDownLatch(1);
        ScheduledExecutorService executorService = getScheduledExecutorService(latch);
        try {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            DeliverCallback deliverCallback = getDeliverCallback();
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {
            });
            logger.info("Waiting for Messages...");
            latch.await();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        finally {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException ex) {
                executorService.shutdownNow();
            }
        }
    }

    /**
     * getScheduledExecutorService: Returns a ScheduledExecutorService object that checks for inactivity every 5 seconds
     * @param latch
     * @return ScheduledExecutorService
     */
    private static ScheduledExecutorService getScheduledExecutorService(CountDownLatch latch) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            logger.info("heartbeat at: "+Instant.now().toString());
            if (lastMessageTime != null && Duration.between(lastMessageTime, Instant.now()).toSeconds() >= 30) {
                try {
                    if (channel != null && channel.isOpen()) {
                        channel.close();
                    }
                    if (connection != null && connection.isOpen()) {
                        connection.close();
                    }
                } catch (IOException | TimeoutException e) {
                    logger.error(e.getMessage());
                    System.exit(1);
                }
                logger.info("No activity for 30 seconds. Shutting down...");
                latch.countDown();
            }
        }, 0, 5, TimeUnit.SECONDS);
        return executorService;
    }

    /**
     * getDeliverCallback: Returns a DeliverCallback object that processes the message and updates the last message time
     * @return DeliverCallback
     */
    private static DeliverCallback getDeliverCallback() {
        ObjectMapper mapper = new ObjectMapper();
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            try {
                logger.info("Message Received: "+new String(message.getBody(), StandardCharsets.UTF_8));
                // Process the message and update the last message time
                StorageService.processData(mapper.readTree(new String(message.getBody(), StandardCharsets.UTF_8)));
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                lastMessageTime = Instant.now();
            } catch (Exception e) {
                logger.error(e.getMessage());
                System.exit(1);
            }
        };
        return deliverCallback;
    }

    /**
     * getChannel: Attempts to establish a connection with the rabbitmq host. If unsuccessful, it will try again
     * after 5 seconds. Program will terminate after 5 unsuccessful attempts.
     * @param factory
     * @throws InterruptedException
     * @throws IOException
     * @throws TimeoutException
     */
    public static void getChannel(ConnectionFactory factory) throws InterruptedException, IOException, TimeoutException {
        int attempts = 0;
        int maxAttempts = 5;
        boolean needConn = true;
        while(needConn) {
            try {
                connection = factory.newConnection();
                channel = connection.createChannel();
                needConn = false;
            } catch (ConnectException e) {
                logger.warn("Failed to Connect to RabbitMQ Host: "+RABBITMQ_HOST);
                if(++attempts<maxAttempts) {
                    logger.warn("Retrying Connection in 5 seconds...");
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    logger.error(e.getMessage());
                    System.exit(1);
                }
            }
        }
    }
}
