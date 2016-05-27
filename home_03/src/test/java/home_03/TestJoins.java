package home_03;

import org.demo.home_03.dao.OrderDao;
import org.demo.home_03.dao.SalesrepDao;
import org.demo.home_03.db.DataSource;
import org.demo.home_03.model.Order;
import org.demo.home_03.model.Salesrep;
import org.junit.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

/**
 * @author dshvedchenko on 5/26/16.
 */
public class TestJoins {

    static DataSource dataSource;
    Connection conn;

    OrderDao orderDao;
    SalesrepDao salesrepDao;

    @BeforeClass
    public static void initSuite() throws SQLException {
        dataSource = DataSource.INSTANCE;
        assertNotNull(dataSource);
    }

    @AfterClass
    public static void closeSuite() {
        dataSource.destroy();
    }

    @Before
    public void initRes() throws SQLException {
        conn = dataSource.getConnection();

    }

    @After
    public void releaseRes() throws SQLException {

        conn.close();
    }

    @Test
    public void querySimpleJoin_p158() throws SQLException {

        Set<Order> orders = new OrderDao(conn).getSimple_p158();

        for( Order order : orders) {
            assertTrue(112989 == order.getORDER_NUM());
            assertEquals(1458.00, order.getAMOUNT(), 0.00001 );
            assertEquals("Jones Mfg.", order.getCUST().getCOMPANY());
            assertEquals(65000.00, order.getCUST().getCREDIT_LIMIT(), 0.0000001);

            break;
        }
    }

    @Test
    public void queryParentChild_p159() throws SQLException {

        Set<Salesrep> salesreps = new SalesrepDao(conn).getParentChild_p159();

        for(Salesrep salesrep: salesreps) {
            assertEquals("Sam Clark", salesrep.getNAME());
            assertEquals("New York", salesrep.getREP_OFFICE().getCITY());
            assertEquals("Eastern", salesrep.getREP_OFFICE().getREGION());

            break;
        }
    }




}
