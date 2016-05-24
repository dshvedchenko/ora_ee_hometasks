package task_02;

import org.junit.*;
import task_02.db.DataSource;
import task_02.util.ValidateRowgainstValues;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author dshvedchenko on 20.05.16.
 */
public class Pages155_188Test {

    static DataSource dataSource;
    Connection conn;
    Statement stmt;

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
        stmt = conn.createStatement();
    }

    @After
    public void releaseRes() throws SQLException {
        stmt.close();
        conn.close();
    }

    @Test
    public void querySimpleJoin_p158() throws SQLException {

        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , COMPANY , CREDIT_LIMIT\n" +
                "FROM ORDERS, CUSTOMERS\n" +
                "WHERE CUST = CUST_NUM";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"112989", "1458.00", "Jones Mfg.", "65000.00"});
    }

    @Test
    public void queryParentChild_p159() throws SQLException {

        String sqlQuery = "SELECT NAME, CITY, REGION\n" +
                "FROM SALESREPS , OFFICES\n" +
                "WHERE REP_OFFICE = OFFICE;";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sam Clark", "New York", "Eastern"});
    }

    @Test
    public void queryParentChild_p161() throws SQLException {

        String sqlQuery = "SELECT CITY, NAME , TITLE\n" +
                "FROM OFFICES , SALESREPS\n" +
                "WHERE MGR = EMPL_NUM;";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"New York", "Sam Clark", "VP Sales"});
    }

    @Test
    public void queryParentChildJoins_p161() throws SQLException {

        String sqlQuery = "SELECT NAME, CITY, REGION\n" +
                "FROM SALESREPS JOIN OFFICES ON\n" +
                "REP_OFFICE = OFFICE;";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sam Clark", "New York", "Eastern"});
    }

    @Test
    public void queryParentChildJoins_p162() throws SQLException {

        String sqlQuery = "SELECT CITY, NAME , TITLE\n" +
                "FROM OFFICES JOIN SALESREPS ON\n" +
                "MGR = EMPL_NUM;";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"New York", "Sam Clark", "VP Sales"});
    }

    @Test
    public void queryParentChildCond_p162() throws SQLException {

        String sqlQuery = "SELECT CITY, NAME , TITLE\n" +
                "FROM OFFICES , SALESREPS\n" +
                "WHERE MGR = EMPL_NUM AND TARGET > 60000";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"New York", "Sam Clark", "VP Sales"});
    }

    @Test
    public void queryParentChildJoinsCond_p163() throws SQLException {

        String sqlQuery = "SELECT CITY, NAME , TITLE\n" +
                "FROM OFFICES JOIN SALESREPS ON\n" +
                "MGR = EMPL_NUM WHERE TARGET > 60000;";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"New York", "Sam Clark", "VP Sales"});
    }

    @Test
    public void queryParentChildJoinsMultiKey_p163() throws SQLException {

        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , DESCRIPTION\n" +
                "FROM ORDERS, PRODUCTS\n" +
                "WHERE MFR = MFR_ID\n" +
                "AND PRODUCT = PRODUCT_ID";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"112992", "760.00", "Size 2 Widget"});

    }

    @Test
    public void queryParentChildJoinsMultiKeyAlter_p163() throws SQLException {

        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , DESCRIPTION\n" +
                "FROM ORDERS JOIN PRODUCTS\n" +
                "ON MFR = MFR_ID\n" +
                "AND PRODUCT = PRODUCT_ID";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"112992", "760.00", "Size 2 Widget"});

    }

    @Test
    public void queryParentChildNaturalJoin_p164() throws SQLException {

        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , DESCRIPTION FROM ORDERS NATURAL JOIN PRODUCTS ;";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"112961", "31500.00", "Size 1 Wiget"});
    }

    @Test
    public void queryParentChildJoinMulti_p164() throws SQLException {

        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , DESCRIPTION FROM ORDERS JOIN PRODUCTS " +
                "ON ORDERS.MFR = PRODUCTS.MFR_ID " +
                "AND ORDERS.PRODUCT = PRODUCTS.PRODUCT_ID;";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"112992", "760.00", "Size 2 Widget"});
    }

    @Test
    public void queryParentChildJoin3Table_p165() throws SQLException {

        String sqlQuery = "SELECT ORDER_NUM, AMOUNT, COMPANY, NAME\n" +
                "FROM ORDERS , CUSTOMERS , SALESREPS\n" +
                "WHERE CUST = CUST_NUM\n" +
                "AND REP = EMPL_NUM\n" +
                "AND AMOUNT > 25000.00;";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"112987", "27500.00", "Acme Mfg.", "Bill Adams"});
    }

    @Test
    public void queryParentChildJoin3TableAlt_p165() throws SQLException {

        String sqlQuery = "SELECT ORDER_NUM, AMOUNT, COMPANY, NAME\n" +
                "FROM ORDERS JOIN CUSTOMERS ON CUST = CUST_NUM \n " +
                "JOIN SALESREPS ON REP = EMPL_NUM\n" +
                "WHERE AMOUNT > 25000.00;";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"112987", "27500.00", "Acme Mfg.", "Bill Adams"});
    }

    @Test
    public void queryParentChildJoin3TableCustRep_p166() throws SQLException {

        String sqlQuery = "SELECT ORDER_NUM, AMOUNT, COMPANY, NAME\n" +
                "FROM ORDERS JOIN CUSTOMERS ON CUST = CUST_NUM \n " +
                "JOIN SALESREPS ON CUST_REP = EMPL_NUM\n" +
                "WHERE AMOUNT > 25000.00;";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"113069", "31350.00", "Chen Associates", "Paul Cruz"});
    }

    @Test
    public void queryParentChildJoin3TableCustRepOffice_p167() throws SQLException {

        String sqlQuery = "SELECT ORDER_NUM, AMOUNT, COMPANY, NAME, CITY\n" +
                "FROM ORDERS , CUSTOMERS , SALESREPS, OFFICES\n" +
                "WHERE CUST = CUST_NUM\n" +
                "AND CUST_REP = EMPL_NUM\n" +
                "AND REP_OFFICE = OFFICE\n" +
                "AND AMOUNT > 25000.00;\n";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"112961", "31350.00", "J.P. Sinclair", "Sam Clark", "New York"});
    }

    @Test
    public void queryParentChildJoinOrderHire_p169() throws SQLException {

        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , ORDER_DATE , NAME\n" +
                "FROM ORDERS , SALESREPS\n" +
                "WHERE ORDER_DATE = HIRE_DATE ;";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"112968", "3978.00", "2007-10-12", "Larry Fitch"});

    }

    @Test
    public void queryParentChildJoinNotEq_p170() throws SQLException {

        String sqlQuery = "SELECT NAME , QUOTA, CITY, TARGET\n" +
                "FROM SALESREPS , OFFICES\n" +
                "WHERE QUOTA > TARGET ";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sue Smith", "350000.00", "Denver", "300000.00"});
    }

    @Test
    public void queryParentChildJoinCityFactOverPlan_p171() throws SQLException {

        String sqlQuery = "SELECT CITY , SALES\n" +
                "FROM OFFICES\n" +
                "WHERE SALES > TARGET";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"New York", "692637.00"});
    }

    @Test
    public void queryParentChildJoinEmplOv350000_p172() throws SQLException {

        String sqlQuery = "SELECT NAME , SALES\n" +
                "FROM SALESREPS\n" +
                "WHERE SALES > 350000";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sue Smith", "474050.00"});
    }

    @Test
    public void queryParentChildJoinEmplOffices_p172() throws SQLException {

        String sqlQuery = "SELECT NAME , SALESREPS.SALES, CITY\n" +
                "FROM SALESREPS, OFFICES\n" +
                "WHERE REP_OFFICE = OFFICE";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sam Clark", "299912.00", "New York"});
    }

    @Test
    public void queryParentChildJoinAllCols_p173() throws SQLException {

        String sqlQuery = "SELECT *" +
                "FROM SALESREPS, OFFICES\n" +
                "WHERE REP_OFFICE = OFFICE";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"106", "Sam Clark", "52", "11", "VP Sales", "2006-06-14", null, "275000.00", "299912.00", "11", "New York", "Eastern", "106", "575000.00", "692637.00"});
    }

    @Test
    public void queryParentChildJoinEmplAllWOffice_p173() throws SQLException {

        String sqlQuery = "SELECT SALESREPS.*, CITY, REGION\n" +
                "FROM SALESREPS, OFFICES\n" +
                "WHERE REP_OFFICE = OFFICE";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"106", "Sam Clark", "52", "11", "VP Sales", "2006-06-14", null, "275000.00", "299912.00",  "New York", "Eastern"});
    }

    @Test
    public void queryParentChildSelfJoin_p174() throws SQLException {

        String sqlQuery = "SELECT NAME, NAME\n" +
                "FROM SALESREPS\n" +
                "WHERE MANAGER = EMPL_NUM";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testNoRows(rs);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"105", "Bill Adams"});
    }

    @Test
    public void queryParentChildSelfJoinEmplMgr_p175() throws SQLException {

        String sqlQuery = "SELECT EMPS.NAME, MGRS.NAME\n" +
                "FROM SALESREPS EMPS, SALESREPS MGRS\n" +
                "WHERE EMPS.MANAGER = MGRS.EMPL_NUM";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "Bob Smith"});
    }

    @Test
    public void queryParentChildSelfJoinEmplMgrObeAlias_p175() throws SQLException {

        String sqlQuery = "SELECT SALESREPS.NAME, MGRS.NAME\n" +
                "FROM SALESREPS, SALESREPS MGRS\n" +
                "WHERE SALESREPS.MANAGER = MGRS.EMPL_NUM";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "Bob Smith"});
    }

    @Test
    public void queryParentChildSelfJoinEmplPlanOverMgrPlan_p175() throws SQLException {

        String sqlQuery = "SELECT SALESREPS.NAME, SALESREPS.QUOTA, MGRS.QUOTA\n" +
                "FROM SALESREPS, SALESREPS MGRS\n" +
                "WHERE SALESREPS.MANAGER = MGRS.EMPL_NUM\n" +
                "AND SALESREPS.QUOTA > MGRS.QUOTA";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "300000.00", "200000.00"});
    }

    @Test
    public void queryParentChildSelfJoinEmplMgrDiffOffices_p176() throws SQLException {

        String sqlQuery = "SELECT EMPS.NAME, EMP_OFFICE.CITY, MGRS.NAME, MGRS_OFFICE.CITY\n" +
                "FROM SALESREPS EMPS, SALESREPS MGRS, OFFICES EMP_OFFICE, OFFICES MGRS_OFFICE\n" +
                "WHERE EMPS.REP_OFFICE = EMP_OFFICE.OFFICE\n" +
                "AND MGRS.REP_OFFICE = MGRS_OFFICE.OFFICE\n" +
                "AND EMPS.MANAGER = MGRS.EMPL_NUM\n" +
                "AND EMPS.REP_OFFICE <> MGRS.REP_OFFICE";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Bob Smith", "Chicago", "Sam Clark", "New York"});
    }

    @Test
    @Ignore(value = "No birthdays table")
    public void queryParentChildSelfJoinEmplBD_Aliases_p176() throws SQLException {

        String sqlQuery = "";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{""});
    }

    @Test
    public void queryParentChildNamexCity_p179() throws SQLException {

        String sqlQuery = "SELECT NAME, CITY FROM SALESREPS, OFFICES";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "New York"});
    }

    @Test
    public void queryParentChildNamesWorkingCity_p180() throws SQLException {

        String sqlQuery = "SELECT NAME, CITY FROM SALESREPS, OFFICES WHERE REP_OFFICE = OFFICE";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sam Clark", "New York"});
    }

    @Test
    public void queryParentChildClient2103_p180() throws SQLException {

        String sqlQuery = "SELECT COMPANY , ORDER_NUM, AMOUNT\n" +
                "FROM CUSTOMERS JOIN ORDERS\n" +
                "ON CUST_NUM = CUST\n" +
                "WHERE CUST_NUM = 2103\n" +
                "ORDER BY ORDER_NUM;";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Acme Mfg.", "112963","3276.00"});
    }
    @Test
    public void queryEmplRepOffice_p182() throws SQLException {

        String sqlQuery = "SELECT NAME, REP_OFFICE FROM SALESREPS";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "12"});
    }

    @Test
    public void queryEmplOffCity_p182() throws SQLException {

        String sqlQuery = "SELECT NAME, CITY FROM SALESREPS JOIN OFFICES ON REP_OFFICE = OFFICE";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sam Clark", "New York"});
    }

    @Test
    public void queryEmplLeftOffCity_p183() throws SQLException {

        String sqlQuery = "SELECT NAME, CITY FROM SALESREPS LEFT JOIN OFFICES ON REP_OFFICE = OFFICE";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "Chicago"});
    }

    @Test
    public void queryEMplOfficesRight_p187() throws SQLException {

        String sqlQuery = "SELECT NAME, CITY FROM SALESREPS LEFT JOIN OFFICES ON REP_OFFICE = OFFICE";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Dan Roberts", "Chicago"});
    }

    @Test
    public void queryOfficeEmplLeft_p188() throws SQLException {

        String sqlQuery = "SELECT CITY, NAME FROM OFFICES LEFT JOIN SALESREPS ON REP_OFFICE = OFFICE";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"New York", "Sam Clark"});
    }



}
