package dao.domain;

import org.json.simple.JSONObject;
/*
Description: Class Light. Domain model for light data
 */
public class Light {

    private String sensorName;
    private String timeStamp;
    private String lux;

    public Light(String sensorName, String timeStamp, String lux) {
        this.sensorName =sensorName;
        this.timeStamp=timeStamp;
        this.lux=lux;
    }

    public Light(JSONObject json) {
        this.sensorName =json.get("sensor_name").toString();
        this.timeStamp=json.get("timestamp").toString();;
        this.lux =((JSONObject) json.get("sensor_data")).get("lux").toString();
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

    public String getLux() {
        return lux;
    }

    public void setLux(String lux) {
        this.lux = lux;
    }
}
