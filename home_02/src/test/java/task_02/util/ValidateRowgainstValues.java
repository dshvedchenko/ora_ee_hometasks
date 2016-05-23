package task_02.util;

import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author dshvedchenko on 22.05.16.
 */
public class ValidateRowgainstValues {
    public static void testOnlyOneRow(ResultSet rs, String[] expectedValues) throws SQLException {

        if (rs.next()) {
            validateOneResult(rs, expectedValues);
        } else {
            Assert.assertEquals("Result must have at least one row", false);
        }

    }

    private static void validateOneResult(ResultSet rs, String[] expectedValues) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();

        Assert.assertEquals("Row and Expected Value Arrays should be equal length",expectedValues.length, rsmd.getColumnCount());

        for (int ix = 1; ix<= rsmd.getColumnCount(); ix++) {
            String expectedmsg = String.format("Column %s expected to have value %s, but have %s", rsmd.getColumnName(ix), expectedValues[ix-1], rs.getString(ix));
            if (expectedValues[ix-1] == null) {
                Assert.assertNull(expectedmsg,rs.getString(ix));
            } else {
                Assert.assertTrue(expectedmsg, expectedValues[ix - 1].equals(rs.getString(ix)));
            }
        }
    }


    public static void testNoRows(ResultSet rs) throws SQLException {
        Assert.assertEquals("No Rows Expected",rs.next(), false);
    }
}
