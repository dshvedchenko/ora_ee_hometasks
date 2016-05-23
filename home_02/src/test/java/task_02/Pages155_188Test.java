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
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sam Clark","New York",  "Eastern"});
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
        ValidateRowgainstValues.testOnlyOneRow(rs, new String[]{"Sam Clark","New York",  "Eastern"});
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


}
