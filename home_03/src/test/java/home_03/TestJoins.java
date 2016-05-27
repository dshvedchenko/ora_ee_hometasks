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

    @Test
    public void queryParentChild_p161() throws SQLException {

        Set<Office> offices = new OfficeDao(conn).getParentChildJoins_p161();
        assertTrue(offices.size() > 0);

        for(Office office : offices) {
            assertEquals("New York", office.getCITY());
            assertEquals("Sam Clark", office.getMGR().getNAME());
            assertEquals("VP Sales", office.getMGR().getTITLE());

            break;
        }
    }

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

    @Test
    public void queryParentChildJoins_p162() throws SQLException {


        Set<Office> offices = new OfficeDao(conn).getParentChildJoins_p162();
        assertTrue(offices.size() > 0);

        for(Office office : offices) {
            assertEquals("New York", office.getCITY());
            assertEquals("Sam Clark", office.getMGR().getNAME());
            assertEquals("VP Sales", office.getMGR().getTITLE());

            break;
        }
    }

    @Test
    public void queryParentChildCond_p162() throws SQLException {
        Set<Office> offices = new OfficeDao(conn).getParentChildJoins_p162();
        assertTrue(offices.size() > 0);

        for(Office office : offices) {
            assertEquals("New York", office.getCITY());
            assertEquals("Sam Clark", office.getMGR().getNAME());
            assertEquals("VP Sales", office.getMGR().getTITLE());

            break;
        }
    }

    @Test
    @Ignore(value = "duplicated 162")
    public void queryParentChildJoinsCond_p163() throws SQLException {

        String sqlQuery = "SELECT CITY, NAME , TITLE\n" +
                "FROM OFFICES JOIN SALESREPS ON\n" +
                "MGR = EMPL_NUM WHERE TARGET > 60000;";

   //     ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"New York", "Sam Clark", "VP Sales"});
    }

    @Test
    public void queryParentChildJoinsMultiKey_p163() throws SQLException {
        Set<Order> orders = new OrderDao(conn).getParentChildJoinsMultiKey_p163();

        assertTrue(orders.size() > 0);

        for(Order order : orders) {
            assertTrue(112992 == order.getORDER_NUM());
            assertEquals(760.00, order.getAMOUNT(), 0.00001);
            assertEquals("Size 2 Widget", order.getPRODUCT().getDESCRIPTION());
        }

    }

    @Test
    public void queryParentChildJoinsMultiKeyAlter_p163() throws SQLException {
        Set<Order> orders = new OrderDao(conn).getParentChildJoinsMultiKeyAlter_p163();

        assertTrue(orders.size() > 0);

        for(Order order : orders) {
            assertTrue(112992 == order.getORDER_NUM());
            assertEquals(760.00, order.getAMOUNT(), 0.00001);
            assertEquals("Size 2 Widget", order.getPRODUCT().getDESCRIPTION());
        }
    }

    @Test
    public void queryParentChildNaturalJoin_p164() throws SQLException {
        Set<Order> orders = new OrderDao(conn).getParentChildNaturalJoin_p164();

        assertTrue(orders.size() > 0);

        for(Order order : orders) {
            assertTrue(112961 == order.getORDER_NUM());
            assertEquals(31500.00, order.getAMOUNT(), 0.00001);
            assertEquals("Size 1 Wiget", order.getPRODUCT().getDESCRIPTION());
        }
    }

    @Test
    public void queryParentChildJoinMulti_p164() throws SQLException {
        Set<Order> orders = new OrderDao(conn).getParentChildJoinMulti_p164();

        assertTrue(orders.size() > 0);

        for(Order order : orders) {
            assertTrue(112992 == order.getORDER_NUM());
            assertEquals(760.00, order.getAMOUNT(), 0.00001);
            assertEquals("Size 2 Widget", order.getPRODUCT().getDESCRIPTION());
        }

   //     ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"112992", "760.00", "Size 2 Widget"});
    }

    @Test
    public void queryParentChildJoin3Table_p165() throws SQLException {
        Set<Order> orders = new OrderDao(conn).getParentChildJoin3Table_p165();

        assertTrue(orders.size() > 0);

        for(Order order : orders) {
            assertTrue(112987 == order.getORDER_NUM());
            assertEquals(27500.00, order.getAMOUNT(), 0.00001);
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
            assertEquals(27500.00, order.getAMOUNT(), 0.00001);
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
            assertEquals(31350.00, order.getAMOUNT(), 0.00001);
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
            assertEquals(31500.00, order.getAMOUNT(), 0.00001);
            assertEquals("J.P. Sinclair", order.getCUST().getCOMPANY());
            assertEquals("Sam Clark", order.getREP().getNAME());
            assertEquals("New York", order.getREP().getREP_OFFICE().getCITY());
        }
    }

    @Test
    public void queryParentChildJoinOrderHire_p169() throws SQLException {

        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , ORDER_DATE , NAME\n" +
                "FROM ORDERS , SALESREPS\n" +
                "WHERE ORDER_DATE = HIRE_DATE ;";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"112968", "3978.00", "2007-10-12", "Larry Fitch"});

    }

    @Test
    public void queryParentChildJoinNotEq_p170() throws SQLException {

        String sqlQuery = "SELECT NAME , QUOTA, CITY, TARGET\n" +
                "FROM SALESREPS , OFFICES\n" +
                "WHERE QUOTA > TARGET ";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sue Smith", "350000.00", "Denver", "300000.00"});
    }

    @Test
    public void queryParentChildJoinCityFactOverPlan_p171() throws SQLException {

        String sqlQuery = "SELECT CITY , SALES\n" +
                "FROM OFFICES\n" +
                "WHERE SALES > TARGET";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"New York", "692637.00"});
    }

    @Test
    public void queryParentChildJoinEmplOv350000_p172() throws SQLException {

        String sqlQuery = "SELECT NAME , SALES\n" +
                "FROM SALESREPS\n" +
                "WHERE SALES > 350000";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sue Smith", "474050.00"});
    }

    @Test
    public void queryParentChildJoinEmplOffices_p172() throws SQLException {

        String sqlQuery = "SELECT NAME , SALESREPS.SALES, CITY\n" +
                "FROM SALESREPS, OFFICES\n" +
                "WHERE REP_OFFICE = OFFICE";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sam Clark", "299912.00", "New York"});
    }

    @Test
    public void queryParentChildJoinAllCols_p173() throws SQLException {

        String sqlQuery = "SELECT *" +
                "FROM SALESREPS, OFFICES\n" +
                "WHERE REP_OFFICE = OFFICE";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"106", "Sam Clark", "52", "11", "VP Sales", "2006-06-14", null, "275000.00", "299912.00", "11", "New York", "Eastern", "106", "575000.00", "692637.00"});
    }

    @Test
    public void queryParentChildJoinEmplAllWOffice_p173() throws SQLException {

        String sqlQuery = "SELECT SALESREPS.*, CITY, REGION\n" +
                "FROM SALESREPS, OFFICES\n" +
                "WHERE REP_OFFICE = OFFICE";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"106", "Sam Clark", "52", "11", "VP Sales", "2006-06-14", null, "275000.00", "299912.00",  "New York", "Eastern"});
    }

    @Test
    public void queryParentChildSelfJoin_p174() throws SQLException {

        String sqlQuery = "SELECT NAME, NAME\n" +
                "FROM SALESREPS\n" +
                "WHERE MANAGER = EMPL_NUM";

    //    ValidateRowgainstValues.testNoRows(rs);
    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"105", "Bill Adams"});
    }

    @Test
    public void queryParentChildSelfJoinEmplMgr_p175() throws SQLException {

        String sqlQuery = "SELECT EMPS.NAME, MGRS.NAME\n" +
                "FROM SALESREPS EMPS, SALESREPS MGRS\n" +
                "WHERE EMPS.MANAGER = MGRS.EMPL_NUM";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "Bob Smith"});
    }

    @Test
    public void queryParentChildSelfJoinEmplMgrObeAlias_p175() throws SQLException {

        String sqlQuery = "SELECT SALESREPS.NAME, MGRS.NAME\n" +
                "FROM SALESREPS, SALESREPS MGRS\n" +
                "WHERE SALESREPS.MANAGER = MGRS.EMPL_NUM";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "Bob Smith"});
    }

    @Test
    public void queryParentChildSelfJoinEmplPlanOverMgrPlan_p175() throws SQLException {

        String sqlQuery = "SELECT SALESREPS.NAME, SALESREPS.QUOTA, MGRS.QUOTA\n" +
                "FROM SALESREPS, SALESREPS MGRS\n" +
                "WHERE SALESREPS.MANAGER = MGRS.EMPL_NUM\n" +
                "AND SALESREPS.QUOTA > MGRS.QUOTA";

   //     ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "300000.00", "200000.00"});
    }

    @Test
    public void queryParentChildSelfJoinEmplMgrDiffOffices_p176() throws SQLException {

        String sqlQuery = "SELECT EMPS.NAME, EMP_OFFICE.CITY, MGRS.NAME, MGRS_OFFICE.CITY\n" +
                "FROM SALESREPS EMPS, SALESREPS MGRS, OFFICES EMP_OFFICE, OFFICES MGRS_OFFICE\n" +
                "WHERE EMPS.REP_OFFICE = EMP_OFFICE.OFFICE\n" +
                "AND MGRS.REP_OFFICE = MGRS_OFFICE.OFFICE\n" +
                "AND EMPS.MANAGER = MGRS.EMPL_NUM\n" +
                "AND EMPS.REP_OFFICE <> MGRS.REP_OFFICE";

   //     ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Bob Smith", "Chicago", "Sam Clark", "New York"});
    }

    @Test
    @Ignore(value = "No birthdays table")
    public void queryParentChildSelfJoinEmplBD_Aliases_p176() throws SQLException {

        String sqlQuery = "";

    //    ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{""});
    }

    @Test
    public void queryParentChildNamexCity_p179() throws SQLException {

        String sqlQuery = "SELECT NAME, CITY FROM SALESREPS, OFFICES";

   //     ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "New York"});
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
