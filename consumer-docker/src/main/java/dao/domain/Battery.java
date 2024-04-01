package dao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/*
Description: Class Battery. Domain model for Battery data
 */
public class Battery {

    @JsonProperty("sensor_name")
    private String sensorName;

    @JsonProperty("timestamp")
    private String timeStamp;

    @JsonProperty("sensor_data")
    private Map<String, String> sensorData;

    public Battery() {
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

    public Map<String, String> getSensorData() {
        return sensorData;
    }

    public void setSensorData(Map<String, String> sensorData) {
        this.sensorData = sensorData;
    }

    public String getPercent() {
        return this.sensorData.get("percent");
    }

    public void setPercent(String percent) {
        this.sensorData.put("percent", percent);
    }

    public String getCharging() {
        return this.sensorData.get("charging");
    }

    public void setCharging(String charging) {
        this.sensorData.put("charging", charging);
    }

}
