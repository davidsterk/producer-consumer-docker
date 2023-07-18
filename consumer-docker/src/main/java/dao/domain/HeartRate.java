package dao.domain;

import org.json.simple.JSONObject;
/*
Description: Class HeartRate. Domain model for heart rate data
 */
public class HeartRate {

    private String sensorName;
    private String timeStamp;
    private String bpm;

    public HeartRate(String sensorName, String timeStamp, String bpm) {
        this.sensorName =sensorName;
        this.timeStamp=timeStamp;
        this.bpm=bpm;
    }

    public HeartRate(JSONObject json) {
        this.sensorName =json.get("sensor_name").toString();
        this.timeStamp=json.get("timestamp").toString();
        this.bpm=((JSONObject) json.get("sensor_data")).get("bpm").toString();
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

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }
}
