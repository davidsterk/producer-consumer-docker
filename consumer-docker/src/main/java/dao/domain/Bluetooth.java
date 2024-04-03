package dao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

/*
Description: Class Bluetooth. Domain model for Bluetooth data
 */
public class Bluetooth {

    @JsonProperty("sensor_name")
    private String sensorName;
    @JsonProperty("timestamp")
    private String timeStamp;
    @JsonProperty("sensor_data")
    private Map<String, String> sensorData;

    public Bluetooth() {}

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

    public String getState() {
        return this.sensorData.get("state");
    }

    public void setState(String state) {
        this.sensorData.put("state", state);
    }
}
