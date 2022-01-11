package dao.sql;

import dao.Dao;
import dao.domain.HeartRate;

import java.sql.Connection;
import java.sql.PreparedStatement;
/*
Class HeartRateDao. Implements Dao interface. Manages the sql DML for HeartRate
 */
public class HeartRateDao implements Dao<HeartRate> {
        private static final String INSERT_STMT = "INSERT INTO heartrate (sensorname, "
            + "timestamp, bpm) Values (?, ?, ?)";

    @Override
    public void create(HeartRate heartRate) throws Exception {
        Connection conn = SqlConnector.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);
            pstmt.setString(1, heartRate.getSensorName());
            pstmt.setString(2, heartRate.getTimeStamp());
            pstmt.setString(3, heartRate.getBpm());
            pstmt.execute();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
