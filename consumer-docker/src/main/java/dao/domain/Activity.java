package dao.domain;

import org.json.simple.JSONObject;
/*
Description: Class Activity. Domain model for Activity data
 */
public class Activity {

        private String sensorName;
        private String timeStamp;
        private String timeStamp1;
        private String stepCounts;
        private String stepDelta;

        public Activity(String sensorName, String timeStamp, String timeStamp1, String stepCounts, String stepDelta ) {
                this.sensorName = sensorName;
                this.timeStamp = timeStamp;
                this.timeStamp1 = timeStamp1;
                this.stepCounts = stepCounts;
                this.stepDelta = stepDelta;
        }

        public Activity(JSONObject json) {
                this.sensorName = json.get("sensor_name").toString();
                this.timeStamp = json.get("timestamp").toString();
                this.timeStamp1 = json.get("time_stamp").toString();
                this.stepCounts = ((JSONObject) json.get("sensor_data")).get("step_counts").toString();
                this.stepDelta = ((JSONObject) json.get("sensor_data")).get("step_delta").toString();
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

        public String getTimeStamp1() {
                return timeStamp1;
        }

        public void setTimeStamp1(String timeStamp1) {
                this.timeStamp1 = timeStamp1;
        }

        public String getStepCounts() {
                return stepCounts;
        }

        public void setStepCounts(String stepCounts) {
                this.stepCounts = stepCounts;
        }

        public String getStepDelta() {
                return stepDelta;
        }

        public void setStepDelta(String stepDelta) {
                this.stepDelta = stepDelta;
        }
}

