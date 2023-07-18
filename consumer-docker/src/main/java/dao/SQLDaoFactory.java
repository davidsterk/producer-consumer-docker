/*
 * Description: Class SQLDaoFactory. Implements the DaoFactory interface
 */
package dao;

import dao.sql.*;
import enums.SensorType;


public class SQLDaoFactory implements DaoFactory {

  private Dao dao;
  private static Dao activFitDao;
  private static Dao activityDao;
  private static Dao batteryDao;
  private static Dao bluetoothDao;
  private static Dao heartRateDao;
  private static Dao lightDao;
  private static Dao nullDao;

  static {
    activFitDao = new ActivFitDao();
    activityDao = new ActivityDao();
    batteryDao = new BatteryDao();
    bluetoothDao = new BluetoothDao();
    heartRateDao = new HeartRateDao();
    lightDao = new LightDao();
    nullDao = new NullDao();
  }

  /*
  Returns a Dao
   */
  @Override
  public Dao getDao(SensorType sensorType) {
    if(sensorType == null) {
      dao = nullDao;
    } else {
      switch(sensorType) {
        case ACTIVITY:
          dao = activityDao;
          break;
        case ACTIVFIT:
          dao = activFitDao;
          break;
        case HEART_RATE:
          dao = heartRateDao;
          break;
        case BATTERY:
          dao = batteryDao;
          break;
        case BLUETOOTH:
          dao = bluetoothDao;
          break;
        case LIGHT:
          dao = lightDao;
          break;
        default:
          dao = nullDao;
          break;
      }
    }
    return dao;
  }
}
