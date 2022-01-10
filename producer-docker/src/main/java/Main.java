/**
 * Main class that runs producer-docker program. The program works as follows:
 * First, the program tries to establish a connection to the rabbitmq host.
 * Second, program opens the input/input.txt file and creates JSON objects for each record and inserts it into the
 * rabbitmq smartwatch queue.
 * Third, after reading the file and inserting into the queue, the program terminates
 */


import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {
    private static final String RABBITMQ_HOST = System.getenv("RABBITMQ_HOST");
    private static final String QUEUE_NAME = "smartwatch";
    private static Channel channel;
    private static Connection connection;

    public static void main(String[] args) throws InterruptedException, IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        getChannel(factory);
        try {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            JSONParser parser = new JSONParser();
            File inputFile = new File("input//input.txt");
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String output;
            while ((output = br.readLine()) != null) {
                output = output.trim();
                try {
                    if (!output.equals("")) {
                        JSONObject json = (JSONObject) parser.parse(output);
                        String sensorType = json.get("sensor_name").toString().toLowerCase();
                        JSONObject message = new JSONObject();
                        message.put("type", sensorType);
                        message.put("contents", json);
                        channel.basicPublish("", QUEUE_NAME, null, message.toString().getBytes(StandardCharsets.UTF_8));
                        System.out.println("Message Sent: " + message.toString());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
            br.close();
            channel.close();
            connection.close();
        } catch (IOException | TimeoutException e) {
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
