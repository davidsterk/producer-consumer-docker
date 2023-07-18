package dao.domain;

import enums.SensorType;
import org.json.simple.JSONObject;
/*
Class DomainFactory, Singleton that returns a new domain object based on the sensortype parameter
 */
public class DomainFactory {

    private DomainFactory() {};
  
    public static Object createModel(SensorType sensorType, JSONObject contents) {
        if(sensorType == null) {
            return new NullDomain();
        }
        switch(sensorType) {
            case ACTIVITY:
                return new Activity(contents);
            case ACTIVFIT:
                return new ActivFit(contents);
            case HEART_RATE:
                return new HeartRate(contents);
            case BATTERY:
               return new Battery(contents);
            case BLUETOOTH:
                return new Bluetooth(contents);
            case LIGHT:
                return new Light(contents);
            default:
                return new NullDomain();
        }
    }
}
