package dao.sql;

import dao.domain.Battery;
import dao.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
/*
Class BatteryDao. Implements Dao interface. Manages the sql DML for Battery
 */
public class BatteryDao implements Dao<Battery> {

    private static final String INSERT_STMT = "INSERT INTO batterysensor (sensorname, "
            + "timestamp, percent, charging) Values (?, ?, ? , ?)";
    @Override
    public void create(Battery battery) throws Exception {
        Connection conn = SqlConnector.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);
            pstmt.setString(1, battery.getSensorName());
            pstmt.setString(2, battery.getTimeStamp());
            pstmt.setString(3, battery.getPercent());
            pstmt.setString(4, battery.getCharging());
            pstmt.execute();
            pstmt.close();
        } catch (Exception e) {
            throw e;
        }
    }
}
