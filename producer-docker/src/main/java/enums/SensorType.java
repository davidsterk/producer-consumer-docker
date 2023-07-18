/*
* Description: enum SensorType. Defines Constants for the different types of data
 */
package enums;

public enum SensorType {
  ACTIVITY("activity"),
  ACTIVFIT("activfit"),
  BLUETOOTH("bt"),
  BATTERY("battery"),
  HEART_RATE("heartrate"),
  LIGHT("light"),
  NULL_TYPE("null"),
  POISON_PILL("poison");
  private String type;

  SensorType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  /*
  Factory method to retrieve an enum
   */
  public static SensorType getSensorType(String type) {
    if(type == null) {
      return NULL_TYPE;
    }
    switch(type) {
      case("activity"):
        return ACTIVITY;
      case("activfit"):
        return ACTIVFIT;
      case("bt"):
        return BLUETOOTH;
      case("battery"):
        return BATTERY;
      case("heartrate"):
        return HEART_RATE;
      case("light"):
        return LIGHT;
      case("poison"):
        return POISON_PILL;
      default:
        return NULL_TYPE;
    }
  }
}
