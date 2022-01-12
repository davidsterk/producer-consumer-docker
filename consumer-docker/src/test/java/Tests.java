import dao.Dao;
import dao.DaoFactory;
import dao.SQLDaoFactory;
import dao.domain.DomainFactory;
import enums.SensorType;
import org.junit.Test;
import static org.junit.Assert.*;
/*
Class Tests runs unit tests
 */
public class Tests {

    /*
    Tests whether the SensorType getSensorType handles nulls
     */
    @Test
    public void testEnum() {
        SensorType sensorType = SensorType.getSensorType(null);
        assertNotNull(sensorType);
    }
    /*
    Tests the DaoFactory creation
     */
    @Test
    public void testDaoFactory() {
        SensorType sensorType = SensorType.getSensorType("battery");
        DaoFactory daoFactory = new SQLDaoFactory();
        Dao dao = daoFactory.getDao(sensorType);
        assertEquals("BatteryDao", dao.getClass().getSimpleName());
    }
    /*
    Tets Domainfactory Creation
     */
    @Test
    public void testDomainFactory() {
        Object domain = DomainFactory.createModel(null,null);
        assertEquals("NullDomain", domain.getClass().getSimpleName());
    }
}
