package task_02.util;

import org.junit.*;
import task_02.db.DataSource;

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
}
