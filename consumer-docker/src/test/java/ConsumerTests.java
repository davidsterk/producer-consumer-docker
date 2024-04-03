import dao.Dao;
import dao.DaoFactory;
import dao.SQLDaoFactory;
import dao.domain.Activity;
import dao.domain.DomainFactory;
import dao.domain.NullDomain;
import dao.sql.BatteryDao;
import enums.SensorType;
import org.junit.jupiter.api.BeforeEach;
import service.StorageService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/*
Class Tests runs unit tests
 */
public class ConsumerTests {

    private DaoFactory daoFactory;
    private Dao dao;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        daoFactory = mock(DaoFactory.class);
        dao = mock(Dao.class);
        objectMapper = new ObjectMapper();

        when(daoFactory.getDao(any(SensorType.class))).thenReturn(dao);
        StorageService.daoFactory = daoFactory;
    }
    @Test
    public void processData_insertsDataIntoDatabase() throws Exception {
        String jsonInput = "{\"type\":\"activity\",\"contents\":{\"sensor_name\":\"Light\",\"timestamp\":\"Tue Feb 28 09:29:41 EST 2017\",\"sensor_data\":{\"lux\":21}}}";
        JsonNode jsonNode = objectMapper.readTree(jsonInput);
        StorageService.processData(jsonNode);
        verify(dao, times(1)).create(any(Activity.class));
    }

    @Test
    public void testDomainFactory() {
        Object domain = DomainFactory.createModel(null,null);
        assertInstanceOf(NullDomain.class, domain);
    }

    @Test
    public void testDaoFactory() {
        SensorType sensorType = SensorType.getSensorType("battery");
        DaoFactory daoFactory = new SQLDaoFactory();
        Dao dao = daoFactory.getDao(sensorType);
        assertInstanceOf(BatteryDao.class,dao);
    }
}
