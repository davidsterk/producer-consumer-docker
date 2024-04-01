package dao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/*
Description: Class Activity. Domain model for Activity data
 */
public class Activity {

        @JsonProperty("sensor_name")
        private String sensorName;
        @JsonProperty("timestamp")
        private String timeStamp;
        @JsonProperty("time_stamp")
        private String timeStamp1;
        @JsonProperty("sensor_data")
        private Map<String, String> sensorData;


        public Activity() {}

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

        public String getTimeStamp1() {
                return timeStamp1;
        }

        public void setTimeStamp1(String timeStamp1) {
                this.timeStamp1 = timeStamp1;
        }

        public Map<String, String> getSensorData() {
                return sensorData;
        }

        public void setSensorData(Map<String, String> sensorData) {
                this.sensorData = sensorData;
        }

        public String getStepCounts() {
                return this.sensorData.get("step_counts");
        }

        public void setStepCounts(String stepCounts) {
                this.sensorData.put("step_counts", stepCounts);
        }

        public String getStepDelta() {
                return this.sensorData.get("step_delta");
        }

        public void setStepDelta(String stepDelta) {
                this.sensorData.put("step_delta", stepDelta);
        }
}

