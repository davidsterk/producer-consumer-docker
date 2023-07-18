package dao.domain;

import org.json.simple.JSONObject;
/*
Description: Class Battery. Domain model for Battery data
 */
public class Battery {

    private String sensorName;
    private String timeStamp;
    private String percent;
    private String charging;

    public Battery(String sensorName, String timeStamp, String percent, String charging) {
        this.sensorName=sensorName;
        this.timeStamp=timeStamp;
        this.percent=percent;
        this.charging=charging;
    }

    public Battery(JSONObject json) {
        this.sensorName=json.get("sensor_name").toString();
        this.timeStamp=json.get("timestamp").toString();
        this.percent=((JSONObject) json.get("sensor_data")).get("percent").toString();
        this.charging=((JSONObject) json.get("sensor_data")).get("charging").toString();
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

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getCharging() {
        return charging;
    }

    public void setCharging(String charging) {
        this.charging = charging;
    }

}
