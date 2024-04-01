package dao.domain;

import enums.SensorType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/*
Class DomainFactory, Singleton that returns a new domain object based on the sensortype parameter
 */
public class DomainFactory {

    private static final ObjectMapper mapper = new ObjectMapper();

    private DomainFactory() {};
  
    public static Object createModel(SensorType sensorType, JsonNode contents) {

        if(sensorType == null) {
            return new NullDomain();
        }
        switch(sensorType) {
            case ACTIVITY:
                return mapper.convertValue(contents, Activity.class);
            case ACTIVFIT:
                return mapper.convertValue(contents, ActivFit.class);
            case HEART_RATE:
                return mapper.convertValue(contents, HeartRate.class);
            case BATTERY:
                return mapper.convertValue(contents, Battery.class);
            case BLUETOOTH:
                return mapper.convertValue(contents, Bluetooth.class);
            case LIGHT:
                return mapper.convertValue(contents, Light.class);
            default:
                return new NullDomain();
        }
    }
}
