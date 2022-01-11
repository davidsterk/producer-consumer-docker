package dao.domain;

import org.json.simple.JSONObject;
/*
Description: Class ActivFit. Domain model for ActivFit data
 */
public class ActivFit {

    private String sensorName;
    private String startTime;
    private String endTime;
    private String activity;
    private String duration;

    public ActivFit(String sensorName, String startTime, String endTime, String activity, String duration ) {
        this.sensorName = sensorName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
        this.duration = duration;
    }

    public ActivFit(JSONObject json) {
        this.sensorName = json.get("sensor_name").toString();
        this.startTime = ((JSONObject) json.get("timestamp")).get("start_time").toString();
        this.endTime = ((JSONObject) json.get("timestamp")).get("end_time").toString();
        this.activity = ((JSONObject) json.get("sensor_data")).get("ActivFit").toString();
        this.duration = ((JSONObject) json.get("sensor_data")).get("duration").toString();
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
