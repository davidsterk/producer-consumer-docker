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
import service.StorageService;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {

    private static final String RABBITMQ_HOST = System.getenv("RABBITMQ_HOST");
    private static final String QUEUE_NAME = "smartwatch";
    private static Connection connection;
    private static Channel channel;

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);

        getChannel(factory);
        try {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            System.out.println("Listening for Messages...");
            JSONParser parser = new JSONParser();
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    StorageService.processData((JSONObject) parser.parse(new String(message.getBody(), StandardCharsets.UTF_8)));
                    System.out.println("Inserted Message");
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            };
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
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
                System.out.println("Failed to Connect to RabbitMQ Host: "+RABBITMQ_HOST);
                if(++attempts<maxAttempts) {
                    System.out.println("Retrying Connection in 5 seconds...");
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
    }
}
