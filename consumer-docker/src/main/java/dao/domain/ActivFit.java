package dao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/*
Description: Class ActivFit. Domain model for ActivFit data
 */
public class ActivFit {

    @JsonProperty("sensor_name")
    private String sensorName;
    @JsonProperty("timestamp")
    private Map<String, String> timeStamp;

    @JsonProperty("sensor_data")
    private Map<String, String> sensorData;

    public ActivFit() {}

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Map<String, String> getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Map<String, String> timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Map<String, String> getSensorData() {
        return sensorData;
    }

    public void setSensorData(Map<String, String> sensorData) {
        this.sensorData = sensorData;
    }

    public String getStartTime() {
        return this.timeStamp.get("start_time");
    }

    public void setStartTime(String startTime) {
        this.timeStamp.put("start_time", startTime);
    }

    public String getEndTime() {
        return this.timeStamp.get("end_time");
    }

    public void setEndTime(String endTime) {
        this.timeStamp.put("end_time", endTime);
    }

    public String getActivity() {
        return this.sensorData.get("ActivFit");
    }

    public void setActivity(String activity) {
        this.sensorData.put("ActivFit", activity);
    }

    public String getDuration() {
        return this.sensorData.get("duration");
    }

    public void setDuration(String duration) {
        this.sensorData.put("duration", duration);
    }
}
