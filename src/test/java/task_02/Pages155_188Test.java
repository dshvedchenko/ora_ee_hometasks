package task_02;

import org.junit.*;
import task_02.db.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

        String sqlQuery = "SELECT ORDER_NUМ, AМOUNT , COMPANY , CREDIT LIMIT\n" +
                "FROM ORDERS , CUSTOMERS\n" +
                "WHERE CUST CUST_NUМ ;";

        ResultSet rs = stmt.executeQuery(sqlQuery);

        while (rs.next()) {
            int ordNum = rs.getInt("ORDER_NUM");
            float amount = rs.getFloat("AMOUNT");
            String company = rs.getString("COMPANY");
            assertEquals(21, qty);
            break;
        }

    }
}
