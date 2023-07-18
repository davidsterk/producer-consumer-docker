package dao.sql;

import dao.Dao;
import dao.domain.Bluetooth;

import java.sql.Connection;
import java.sql.PreparedStatement;
/*
Class BluetoothDao. Implements Dao interface. Manages the sql DML for Bluetooth
 */
public class BluetoothDao implements Dao<Bluetooth> {

    private static final String INSERT_STMT = "INSERT INTO bluetooth (sensorname, "
            + "timestamp, state) Values (?, ?, ?)";
    @Override
    public void create(Bluetooth bluetooth) throws Exception {
        Connection conn = SqlConnector.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);
            pstmt.setString(1, bluetooth.getSensorName());
            pstmt.setString(2, bluetooth.getTimeStamp());
            pstmt.setString(3, bluetooth.getState());
            pstmt.execute();
            pstmt.close();
        } catch (Exception e) {
            throw e;
        }
    }
}
