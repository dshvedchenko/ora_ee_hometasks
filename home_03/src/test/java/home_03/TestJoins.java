package home_03;

import org.demo.home_03.dao.OfficeDao;
import org.demo.home_03.dao.OrderDao;
import org.demo.home_03.dao.SalesrepDao;
import org.demo.home_03.db.DataSource;
import org.demo.home_03.model.Office;
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
    OfficeDao officeDao;

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
            assertEquals(1458.00, order.getAMOUNT());
            assertEquals("Jones Mfg.", order.getCUST().getCOMPANY());
            assertEquals(65000.00, order.getCUST().getCREDIT_LIMIT());

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

//    @Test
//    public void queryParentChild_p161() throws SQLException {
//
//        Set<Office> offices = new OfficeDao(conn).getParentChildJoins_p161();
//        assertTrue(offices.size() > 0);
//
//        for(Office office : offices) {
//            assertEquals("New York", office.getCITY());
//            assertEquals("Sam Clark", office.getMGR().getNAME());
//            assertEquals("VP Sales", office.getMGR().getTITLE());
//
//            break;
//        }
//    }

    @Test
    public void queryParentChildJoins_p161() throws SQLException {

        Set<Salesrep> salesreps = new SalesrepDao(conn).getParentChild_p161();
        assertTrue(salesreps.size() > 0);

        for(Salesrep salesrep: salesreps) {
            assertEquals("Sam Clark", salesrep.getNAME());
            assertEquals("New York", salesrep.getREP_OFFICE().getCITY());
            assertEquals("Eastern", salesrep.getREP_OFFICE().getREGION());

            break;
        }
    }

//    @Test
//    public void queryParentChildJoins_p162() throws SQLException {
//
//
//        Set<Office> offices = new OfficeDao(conn).getParentChildJoins_p162();
//        assertTrue(offices.size() > 0);
//
//        for(Office office : offices) {
//            assertEquals("New York", office.getCITY());
//            assertEquals("Sam Clark", office.getMGR().getNAME());
//            assertEquals("VP Sales", office.getMGR().getTITLE());
//
//            break;
//        }
//    }
//
//    @Test
//    public void queryParentChildCond_p162() throws SQLException {
//        Set<Office> offices = new OfficeDao(conn).getParentChildJoins_p162();
//        assertTrue(offices.size() > 0);
//
//        for(Office office : offices) {
//            assertEquals("New York", office.getCITY());
//            assertEquals("Sam Clark", office.getMGR().getNAME());
//            assertEquals("VP Sales", office.getMGR().getTITLE());
//
//            break;
//        }
//    }

    @Test
    @Ignore(value = "duplicated 162")
    public void queryParentChildJoinsCond_p163() throws SQLException {

        String sqlQuery = "SELECT CITY, NAME , TITLE\n" +
                "FROM OFFICES JOIN SALESREPS ON\n" +
                "MGR = EMPL_NUM WHERE TARGET > 60000;";

   //     ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"New York", "Sam Clark", "VP Sales"});
    }

//    @Test
//    public void queryParentChildJoinsMultiKey_p163() throws SQLException {
//        Set<Order> orders = new OrderDao(conn).getParentChildJoinsMultiKey_p163();
//
//        assertTrue(orders.size() > 0);
//
//        for(Order order : orders) {
//            assertTrue(112992 == order.getORDER_NUM());
//            assertEquals(760.00, order.getAMOUNT(), 0.00001);
//            assertEquals("Size 2 Widget", order.getPRODUCT().getDESCRIPTION());
//        }
//
//    }

//    @Test
//    public void queryParentChildJoinsMultiKeyAlter_p163() throws SQLException {
//        Set<Order> orders = new OrderDao(conn).getParentChildJoinsMultiKeyAlter_p163();
//
//        assertTrue(orders.size() > 0);
//
//        for(Order order : orders) {
//            assertTrue(112992 == order.getORDER_NUM());
//            assertEquals(760.00, order.getAMOUNT(), 0.00001);
//            assertEquals("Size 2 Widget", order.getPRODUCT().getDESCRIPTION());
//        }
//    }
//
//    @Test
//    public void queryParentChildNaturalJoin_p164() throws SQLException {
//        Set<Order> orders = new OrderDao(conn).getParentChildNaturalJoin_p164();
//
//        assertTrue(orders.size() > 0);
//
//        for(Order order : orders) {
//            assertTrue(112961 == order.getORDER_NUM());
//            assertEquals(31500.00, order.getAMOUNT(), 0.00001);
//            assertEquals("Size 1 Wiget", order.getPRODUCT().getDESCRIPTION());
//        }
//    }
//
//    @Test
//    public void queryParentChildJoinMulti_p164() throws SQLException {
//        Set<Order> orders = new OrderDao(conn).getParentChildJoinMulti_p164();
//
//        assertTrue(orders.size() > 0);
//
//        for(Order order : orders) {
//            assertTrue(112992 == order.getORDER_NUM());
//            assertEquals(760.00, order.getAMOUNT(), 0.00001);
//            assertEquals("Size 2 Widget", order.getPRODUCT().getDESCRIPTION());
//        }
//
//   //     ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"112992", "760.00", "Size 2 Widget"});
//    }

    @Test
    public void queryParentChildJoin3Table_p165() throws SQLException {
        Set<Order> orders = new OrderDao(conn).getParentChildJoin3Table_p165();

        assertTrue(orders.size() > 0);

        for(Order order : orders) {
            assertTrue(112987 == order.getORDER_NUM());
            assertEquals(27500.00, order.getAMOUNT());
            assertEquals("Acme Mfg.", order.getCUST().getCOMPANY());
            assertEquals("Bill Adams", order.getREP().getNAME());
        }

    }

    @Test
    public void queryParentChildJoin3TableAlt_p165() throws SQLException {
        Set<Order> orders = new OrderDao(conn).getParentChildJoin3TableAlt_p165();
        assertTrue(orders.size() > 0);

        for(Order order : orders) {
            assertTrue(112987 == order.getORDER_NUM());
            assertEquals(27500.00, order.getAMOUNT());
            assertEquals("Acme Mfg.", order.getCUST().getCOMPANY());
            assertEquals("Bill Adams", order.getREP().getNAME());
        }
    }

    @Test
    public void queryParentChildJoin3TableCustRep_p166() throws SQLException {
        Set<Order> orders = new OrderDao(conn).getParentChildJoin3TableCustRep_p166();
        assertTrue(orders.size() > 0);

        for(Order order : orders) {
            assertTrue(113069 == order.getORDER_NUM());
            assertEquals(31350.00, order.getAMOUNT());
            assertEquals("Chen Associates", order.getCUST().getCOMPANY());
            assertEquals("Paul Cruz", order.getREP().getNAME());
        }
    }

    @Test
    public void queryParentChildJoin3TableCustRepOffice_p167() throws SQLException {
        Set<Order> orders = new OrderDao(conn).getParentChildJoin3TableCustRepOffice_p167();
        assertTrue(orders.size() > 0);

        for(Order order : orders) {
            assertTrue(112961 == order.getORDER_NUM());
            assertEquals(31500.00, order.getAMOUNT());
            assertEquals("J.P. Sinclair", order.getCUST().getCOMPANY());
            assertEquals("Sam Clark", order.getREP().getNAME());
            assertEquals("New York", order.getREP().getREP_OFFICE().getCITY());
        }
    }

    @Test
    public void queryParentChildJoinOrderHire_p169() throws SQLException {

        Set<Order> orders = new OrderDao(conn).getParentChildJoinOrderHire_p169();
        assertTrue(orders.size() > 0);

        for(Order order : orders) {
            assertTrue(112968 == order.getORDER_NUM());
            assertEquals(3978.00, order.getAMOUNT());
            assertEquals("2007-10-12", order.getORDER_DATE().toString());
            assertEquals("Larry Fitch", order.getREP().getNAME());
        }
    }

    @Test
    public void queryParentChildJoinNotEq_p170() throws SQLException {
        Set<Salesrep> salesreps = new SalesrepDao(conn).getParentChildJoinNotEq_p170();
        for(Salesrep salesrep : salesreps) {
            assertEquals("Sue Smith", salesrep.getNAME());
            assertEquals(350000.00, salesrep.getQUOTA());
            assertEquals("Denver", salesrep.getREP_OFFICE().getCITY());
            assertEquals(300000.00, salesrep.getREP_OFFICE().getTARGET());
        }
    }

    @Test
    public void queryParentChildJoinCityFactOverPlan_p171() throws SQLException {

        Set<Office> offices = new OfficeDao(conn).getParentChildJoinCityFactOverPlan_p171();
        assertTrue(offices.size() > 0);

        for(Office office : offices) {
            assertEquals("New York", office.getCITY());
            assertEquals(692637.00, office.getSALES());

            break;
        }
    }

    @Test
    public void queryParentChildJoinEmplOv350000_p172() throws SQLException {
        Set<Salesrep> salesreps = new SalesrepDao(conn).getParentChildJoinEmplOv350000_p172();
        assertTrue(salesreps.size() > 0);

        for(Salesrep salesrep: salesreps) {
            assertEquals("Sue Smith", salesrep.getNAME());
            assertEquals(474050.00, salesrep.getSALES());
            break;
        }
    }

    @Test
    public void queryParentChildJoinEmplOffices_p172() throws SQLException {
        Set<Salesrep> salesreps = new SalesrepDao(conn).getParentChildJoinEmplOffices_p172();
        assertTrue(salesreps.size() > 0);

        for(Salesrep salesrep: salesreps) {
            assertEquals("Sam Clark", salesrep.getNAME());
            assertEquals(299912.00, salesrep.getSALES());
            assertEquals("New York", salesrep.getREP_OFFICE().getCITY());
            break;
        }
    }

    @Test
    public void queryParentChildJoinAllCols_p173() throws SQLException {
        Set<Salesrep> salesreps = new SalesrepDao(conn).getParentChildJoinAllCols_p173();
        assertTrue(salesreps.size() > 0);

        for(Salesrep salesrep: salesreps) {
            assertEquals("Sam Clark", salesrep.getNAME());
            assertEquals(299912.00, salesrep.getSALES());
            assertEquals("New York", salesrep.getREP_OFFICE().getCITY());
            assertEquals(692637.00, salesrep.getREP_OFFICE().getSALES());
            break;
        }

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"106", "Sam Clark", "52", "11", "VP Sales", "2006-06-14", null, "275000.00", "299912.00", "11", "New York", "Eastern", "106", "575000.00", "692637.00"});
    }

    @Test
    public void queryParentChildJoinEmplAllWOffice_p173() throws SQLException {
        Set<Salesrep> salesreps = new SalesrepDao(conn).queryParentChildJoinEmplAllWOffice_p173();
        assertTrue(salesreps.size() > 0);

        for(Salesrep salesrep: salesreps) {
            assertEquals("Sam Clark", salesrep.getNAME());
            assertEquals(299912.00, salesrep.getSALES());
            assertEquals("New York", salesrep.getREP_OFFICE().getCITY());
            assertEquals("Eastern", salesrep.getREP_OFFICE().getREGION());
            break;
        }
    }


    @Test
    public void queryParentChildSelfJoinEmplMgr_p175() throws SQLException {
        Set<Salesrep> salesreps = new SalesrepDao(conn).queryParentChildSelfJoinEmplMgr_p175();
        assertTrue(salesreps.size() > 0);
        for(Salesrep salesrep: salesreps) {
            assertEquals("Dan Roberts", salesrep.getNAME());
            assertEquals("Bob Smith", salesrep.getMANAGER().getNAME());
            break;
        }
    }

    @Test
    public void queryParentChildSelfJoinEmplMgrObeAlias_p175() throws SQLException {
        Set<Salesrep> salesreps = new SalesrepDao(conn).queryParentChildSelfJoinEmplMgrObeAlias_p175();
        assertTrue(salesreps.size() > 0);
        for(Salesrep salesrep: salesreps) {
            assertEquals("Dan Roberts", salesrep.getNAME());
            assertEquals("Bob Smith", salesrep.getMANAGER().getNAME());
            break;
        }
    }

    @Test
    public void queryParentChildSelfJoinEmplPlanOverMgrPlan_p175() throws SQLException {
        Set<Salesrep> salesreps = new SalesrepDao(conn).queryParentChildSelfJoinEmplPlanOverMgrPlan_p175();
        assertTrue(salesreps.size() > 0);
        for(Salesrep salesrep: salesreps) {
            assertEquals("Dan Roberts", salesrep.getNAME());
            assertEquals(300000.00, salesrep.getQUOTA());
            assertEquals(200000.00, salesrep.getMANAGER().getQUOTA());
            break;
        }
    }

    @Test
    public void queryParentChildSelfJoinEmplMgrDiffOffices_p176() throws SQLException {
        Set<Salesrep> salesreps = new SalesrepDao(conn).queryParentChildSelfJoinEmplMgrDiffOffices_p176();
        assertTrue(salesreps.size() > 0);
        for(Salesrep salesrep: salesreps) {
            assertEquals("Bob Smith", salesrep.getNAME());
            assertEquals("Chicago", salesrep.getREP_OFFICE().getCITY());
            assertEquals("Sam Clark", salesrep.getMANAGER().getNAME());
            assertEquals("New York", salesrep.getMANAGER().getREP_OFFICE().getCITY());
            break;
        }
    }

    @Test
    public void queryParentChildNamexCity_p179() throws SQLException {
        Set<Salesrep> salesreps = new SalesrepDao(conn).queryParentChildNamexCity_p179();
        assertTrue(salesreps.size() > 0);
        for(Salesrep salesrep: salesreps) {
            assertEquals("Dan Roberts", salesrep.getNAME());
            assertEquals("New York", salesrep.getREP_OFFICE().getCITY());
            break;
        }
    }

    @Test
    public void queryParentChildNamesWorkingCity_p180() throws SQLException {

        String sqlQuery = "SELECT NAME, CITY FROM SALESREPS, OFFICES WHERE REP_OFFICE = OFFICE";

   //     ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sam Clark", "New York"});
    }

    @Test
    public void queryParentChildClient2103_p180() throws SQLException {

        String sqlQuery = "SELECT COMPANY , ORDER_NUM, AMOUNT\n" +
                "FROM CUSTOMERS JOIN ORDERS\n" +
                "ON CUST_NUM = CUST\n" +
                "WHERE CUST_NUM = 2103\n" +
                "ORDER BY ORDER_NUM;";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Acme Mfg.", "112963","3276.00"});
    }
    @Test
    public void queryEmplRepOffice_p182() throws SQLException {

        String sqlQuery = "SELECT NAME, REP_OFFICE FROM SALESREPS";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "12"});
    }

    @Test
    public void queryEmplOffCity_p182() throws SQLException {

        String sqlQuery = "SELECT NAME, CITY FROM SALESREPS JOIN OFFICES ON REP_OFFICE = OFFICE";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sam Clark", "New York"});
    }

    @Test
    public void queryEmplLeftOffCity_p183() throws SQLException {

        String sqlQuery = "SELECT NAME, CITY FROM SALESREPS LEFT JOIN OFFICES ON REP_OFFICE = OFFICE";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "Chicago"});
    }

    @Test
    public void queryEMplOfficesRight_p187() throws SQLException {

        String sqlQuery = "SELECT NAME, CITY FROM SALESREPS LEFT JOIN OFFICES ON REP_OFFICE = OFFICE";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "Chicago"});
    }

    @Test
    public void queryOfficeEmplLeft_p188() throws SQLException {

        String sqlQuery = "SELECT CITY, NAME FROM OFFICES LEFT JOIN SALESREPS ON REP_OFFICE = OFFICE";

     //   ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"New York", "Sam Clark"});
    }




}
