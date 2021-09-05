package service;

import dao.*;
import dao.domain.*;
import enums.SensorType;
import org.json.simple.JSONObject;

public class StorageService {

    private static final DaoFactory daoFactory;

    private StorageService(){}

    static {
        daoFactory = new SQLDaoFactory();
    }
    public static void processData(JSONObject json) throws Exception {
        SensorType sensorType = SensorType.getSensorType(json.get("type").toString());
        Dao dao = daoFactory.getDao(sensorType);
        JSONObject contents = (JSONObject) json.get("contents");
        Object domain;
        switch(sensorType) {
            case ACTIVITY:
                domain = new Activity(contents);
                domain = (Activity) domain;
                break;
            case ACTIVFIT:
                domain = new ActivFit(contents);
                domain = (ActivFit) domain;
                break;
            case HEART_RATE:
                domain = new HeartRate(contents);
                domain = (HeartRate) domain;
                break;
            case BATTERY:
                domain = new Battery(contents);
                domain = (Battery) domain;
                break;
            case BLUETOOTH:
                domain = new Bluetooth(contents);
                domain = (Bluetooth) domain;
                break;
            case LIGHT:
                domain = new Light(contents);
                domain = (Light) domain;
                break;
            default:
                domain = new NullDomain();
                break;
        }
        dao.create(domain);
    }
}
