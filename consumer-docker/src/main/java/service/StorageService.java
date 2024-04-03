package service;
/*
Class StorageService, Singleton that assigns the message object to the appropriate domain object
 */
import dao.*;
import dao.domain.*;
import enums.SensorType;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorageService {

    private static Logger logger = LoggerFactory.getLogger(StorageService.class);
    public static DaoFactory daoFactory;

    private StorageService(){}

/*
Initialize daoFactory
 */
    static {
        daoFactory = new SQLDaoFactory();
    }

    /**
     * method ProcessData. Uses the daoFactory to assign the message to the appropriate domain object based on the
     * sensor type. The sensor data contained within the message is inserted mysql
     * @param json
     * @throws Exception
     */
    public static void processData(JsonNode json) throws Exception {
        SensorType sensorType = SensorType.getSensorType(json.get("type").asText());
        JsonNode contents = json.get("contents");
        Dao dao = daoFactory.getDao(sensorType);
        dao.create(DomainFactory.createModel(sensorType, contents));
        logger.info(sensorType.getType() + " object inserted into msyql");
    }
}
