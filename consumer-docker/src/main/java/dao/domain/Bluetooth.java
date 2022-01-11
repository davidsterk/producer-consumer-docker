package dao.domain;

import org.json.simple.JSONObject;
/*
Description: Class Bluetooth. Domain model for Bluetooth data
 */
public class Bluetooth {

    private String sensorName;
    private String timeStamp;
    private String state;

    public Bluetooth(String sensorName, String timeStamp, String state) {
        this.sensorName =sensorName;
        this.timeStamp=timeStamp;
        this.state=state;
    }

    public Bluetooth(JSONObject json) {
        this.sensorName =json.get("sensor_name").toString();
        this.timeStamp=json.get("timestamp").toString();
        this.state=((JSONObject) json.get("sensor_data")).get("state").toString();
    }
    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
