package dao.sql;

import dao.domain.Activity;
import dao.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/*
Class ActivityDao. Implements Dao interface. Manages the sql DML for Activity
 */
public class ActivityDao implements Dao<Activity> {
        private final static String INSERT_STMT = "INSERT INTO activity(sensorname, "
            + "timestamp, time_stamp, stepcounts, stepdelta) Values (?, ?, ?, ?, ?)";
    @Override
    public void create(Activity activity) throws Exception {
        Connection conn = SqlConnector.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);
            pstmt.setString(1, activity.getSensorName());
            pstmt.setString(2, activity.getTimeStamp());
            pstmt.setString(3, activity.getTimeStamp1());
            pstmt.setString(4, activity.getStepCounts());
            pstmt.setString(5, activity.getStepDelta());
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            throw e;
        }
    }
}
