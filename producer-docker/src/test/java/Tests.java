import enums.SensorType;
import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {

    /*
Tests whether the SensorType getSensorType handles nulls
 */
    @Test
    public void testNUllEnum() {
        SensorType sensorType = SensorType.getSensorType(null);
        assertNotNull(sensorType);
    }

    /*
Tests wether SensorType getSensorType returns Battery enum for 'battery'
*/
    @Test
    public void testBatteryEnum() {
        SensorType sensorType = SensorType.getSensorType("battery");
        assertEquals("battery",sensorType.getType());
    }

    /*
Tests wether SensorType getSensorType returns default enum for unexpected value
*/
    @Test
    public void testEnumBadValue() {
        SensorType sensorType = SensorType.getSensorType("bloodpressure");
        assertEquals("null",sensorType.getType());
    }
}
