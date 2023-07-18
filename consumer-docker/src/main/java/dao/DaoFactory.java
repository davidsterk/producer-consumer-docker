/*
 * Description: Interface DaoFactory. Defines the getDao method
 */
package dao;

import enums.SensorType;
public interface DaoFactory {

  /*
  Used by concrete implementations to pick a strategy
   */
  Dao getDao(SensorType sensorType);
}
