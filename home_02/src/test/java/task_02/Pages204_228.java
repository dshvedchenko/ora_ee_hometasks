package task_02;

import org.junit.*;
import task_02.db.DataSource;
import task_02.util.ValidateRowgainstValues;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertNotNull;

/**
 * @author dshvedchenko on 5/24/16.
 */
public class Pages204_228 {

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
    public void queryAvrg1_p204() throws SQLException {

        String sqlQuery = "SELECT AVG(QUOTA),AVG(SALES) FROM SALESREPS";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"300000.000000", "289353.200000"});
    }

    @Test
    public void queryAvrg2_p205() throws SQLException {

        String sqlQuery = "SELECT AVG(100 * (SALES/QUOTA)) FROM SALESREPS";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"102.6001495911"});
    }

    @Test
    public void querySum1_p206() throws SQLException {

        String sqlQuery = "SELECT SUM(QUOTA),SUM(SALES) FROM SALESREPS";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"2700000.00", "2893532.00"});
    }

    @Test
    public void querySum2_p207() throws SQLException {

        String sqlQuery = "SELECT SUM(AMOUNT) FROM ORDERS, SALESREPS WHERE NAME = 'Bill Adams' AND REP = EMPL_NUM";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"39327.00"});
    }

    @Test
    public void queryAbg3_p207() throws SQLException {

        String sqlQuery = "SELECT AVG(PRICE) FROM PRODUCTS WHERE MFR_ID = 'ACI' ";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"804.285714"});
    }

    @Test
    public void queryAbg4_p207() throws SQLException {

        String sqlQuery = "SELECT AVG(AMOUNT) FROM ORDERS WHERE CUST = 2103 ";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"8895.500000"});
    }

    @Test
    public void queryMaXMin1_207() throws SQLException {

        String sqlQuery = "SELECT MIN(QUOTA), MAX(QUOTA) FROM SALESREPS";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"200000.00", "350000.00"});
    }

    @Test
    public void queryMaXMin2_207() throws SQLException {

        String sqlQuery = "SELECT MIN(ORDER_DATE) FROM ORDERS";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"2007-01-04"});
    }

    @Test
    public void queryMaXMin3_207() throws SQLException {

        String sqlQuery = "SELECT MAX(100* (SALES/QUOTA)) FROM SALESREPS";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"135.442857"});
    }

    @Test
    public void queryCount_208() throws SQLException {

        String sqlQuery = "SELECT COUNT(CUST_NUM) FROM CUSTOMERS";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"21"});
    }

    @Test
    public void queryEmplOverSales_208() throws SQLException {

        String sqlQuery = "SELECT COUNT(NAME) FROM SALESREPS WHERE SALES > QUOTA";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"7"});
    }

    @Test
    public void queryorderGr25000_209() throws SQLException {

        String sqlQuery = "SELECT COUNT(AMOUNT) FROM ORDERS WHERE AMOUNT > 25000.00";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"4"});
    }

    @Test
    public void queryordersGr25000CountWild_209() throws SQLException {

        String sqlQuery = "SELECT COUNT(*) FROM ORDERS WHERE AMOUNT > 25000.00";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"4"});
    }

    @Test
    public void queryCplxAvgSum_210() throws SQLException {

        String sqlQuery = "SELECT AVG(AMOUNT), SUM(AMOUNT), " +
                "(100* AVG(AMOUNT/CREDIT_LIMIT)), " +
                "(100* AVG(AMOUNT/QUOTA)) " +
                "FROM ORDERS, CUSTOMERS, SALESREPS " +
                "WHERE CUST=CUST_NUM " +
                "AND REP = EMPL_NUM";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"8256.366667","247691.00","24.4453864691","2.5065790043"});
    }

    @Test
    public void queryCplxSum_211() throws SQLException {

        String sqlQuery = "SELECT AMOUNT, AMOUNT, " +
                "100* (AMOUNT/CREDIT_LIMIT), " +
                "100* (AMOUNT/QUOTA) " +
                "FROM ORDERS, CUSTOMERS, SALESREPS " +
                "WHERE CUST=CUST_NUM " +
                "AND REP = EMPL_NUM";

        ResultSet rs = stmt.executeQuery(sqlQuery);
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"3978.00","3978.00","6.120000","1.326000"});
    }
}