package dao.sql;

import dao.Dao;
import dao.domain.Light;

import java.sql.Connection;
import java.sql.PreparedStatement;
/*
Class LightDao. Implements Dao interface. Manages the sql DML for Light
 */
public class LightDao implements Dao<Light> {

    private static final String INSERT_STMT = "INSERT INTO lightsensor (sensorname, "
            + "timestamp, lux) Values (?, ?, ?)";

    @Override
    public void create(Light light) throws Exception {
        Connection conn = SqlConnector.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);
            pstmt.setString(1, light.getSensorName());
            pstmt.setString(2, light.getTimeStamp());
            pstmt.setString(3, light.getLux());
            pstmt.execute();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
