
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.StorageService;


public class Main {

    private static final String RABBITMQ_HOST = System.getenv("RABBITMQ_HOST");
    private static final String QUEUE_NAME = "smartwatch";

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false,null);
        System.out.println("Listening for Messages...")
        JSONParser parser = new JSONParser();
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            try {
                StorageService.processData((JSONObject) parser.parse(new String(message.getBody(), "UTF-8")));;
                System.out.println("Inserted Message");
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        };
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }

}
