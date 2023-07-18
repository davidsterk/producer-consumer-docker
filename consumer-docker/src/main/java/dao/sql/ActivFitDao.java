package dao.sql;

import dao.domain.ActivFit;
import dao.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
/*
Class ActivityFitDao. Implements Dao interface. Manages the sql DML for ActivityFit
 */
public class ActivFitDao implements Dao<ActivFit> {

    private static final String INSERT_STMT = "INSERT INTO activfit(sensorname, "
            + "starttime, endtime, activity, duration) Values (?, ?, ?, ?, ?)";

    @Override
    public void create(ActivFit activFit) throws Exception {
        Connection conn = SqlConnector.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);
            pstmt.setString(1, activFit.getSensorName());
            pstmt.setString(2, activFit.getStartTime());
            pstmt.setString(3, activFit.getEndTime());
            pstmt.setString(4, activFit.getActivity());
            pstmt.setString(5, activFit.getDuration());
            pstmt.execute();
            pstmt.close();
        } catch (Exception e) {
            throw e;
        }
    }
}
