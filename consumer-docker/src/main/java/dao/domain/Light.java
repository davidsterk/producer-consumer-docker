package dao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
/*
Description: Class Light. Domain model for light data
 */
public class Light {

    @JsonProperty("sensor_name")
    private String sensorName;
    @JsonProperty("timestamp")
    private String timeStamp;
    @JsonProperty("sensor_data")
    private Map<String, String> sensorData;

    public Light() {}

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

    public String getLux() {
        return this.sensorData.get("lux");
    }

    public void setLux(String lux) {
        this.sensorData.put("lux", lux);
    }
}
