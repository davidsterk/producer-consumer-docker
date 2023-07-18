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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.StorageService;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String RABBITMQ_HOST = System.getenv("RABBITMQ_HOST");
    private static final String QUEUE_NAME = "smartwatch";
    private static Connection connection;
    private static Channel channel;

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);

        getChannel(factory);
        logger.info("Connected to RabbitMQ Channel: "+channel.getChannelNumber());
        try {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            logger.info("Listening for Messages...");
            JSONParser parser = new JSONParser();
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    StorageService.processData((JSONObject) parser.parse(new String(message.getBody(), StandardCharsets.UTF_8)));
                    logger.info("Message Processed");
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    System.exit(1);
                }
            };
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
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
