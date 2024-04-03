package dao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/*
Description: Class HeartRate. Domain model for heart rate data
 */
public class HeartRate {

    @JsonProperty("sensor_name")
    private String sensorName;
    @JsonProperty("timestamp")
    private String timeStamp;
    @JsonProperty("sensor_data")
    private Map<String, String> sensorData;

    public HeartRate() { }

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

    public Map<String, String> getSensorData() {
        return sensorData;
    }

    public void setSensorData(Map<String, String> sensorData) {
        this.sensorData = sensorData;
    }

    public String getBpm() {
        return this.sensorData.get("bpm");
    }

    public void setBpm(String bpm) {
        this.sensorData.put("bpm", bpm);
    }

}
