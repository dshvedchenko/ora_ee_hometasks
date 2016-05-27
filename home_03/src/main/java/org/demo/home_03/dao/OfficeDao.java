package org.demo.home_03.dao;

import org.demo.home_03.model.Customer;
import org.demo.home_03.model.Office;
import org.demo.home_03.model.Order;
import org.demo.home_03.model.Salesrep;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author dshvedchenko on 5/27/16.
 */
public class OfficeDao {
    private final Connection connection;

    public OfficeDao(Connection connection) {
        this.connection = connection;
    }

    public Set<Office> getParentChildJoins_p161() {
        Set<Office> offices = new LinkedHashSet<>(0);
        Statement statement = null;

        String sqlQuery = "SELECT OFFICE, CITY, EMPL_NUM, NAME , TITLE\n" +
                "FROM OFFICES , SALESREPS\n" +
                "WHERE MGR = EMPL_NUM;";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Office office = new Office();
                office.setOFFICE(rs.getInt("OFFICE"));
                office.setCITY(rs.getString("CITY"));
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                salesrep.setTITLE(rs.getString("TITLE"));
                office.setMGR(salesrep);
                offices.add(office);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return offices;
    }

    public Set<Office> getParentChildJoins_p162() {
        Set<Office> offices = new LinkedHashSet<>(0);
        Statement statement = null;

        String sqlQuery = "SELECT OFFICE, CITY, EMPL_NUM, NAME, TITLE\n" +
                "FROM OFFICES JOIN SALESREPS ON\n" +
                "MGR = EMPL_NUM;";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Office office = new Office();
                office.setOFFICE(rs.getInt("OFFICE"));
                office.setCITY(rs.getString("CITY"));
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                salesrep.setTITLE(rs.getString("TITLE"));
                office.setMGR(salesrep);
                offices.add(office);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return offices;
    }

    public Set<Office> getParentChildCond_p162() {
        Set<Office> offices = new LinkedHashSet<>(0);
        Statement statement = null;

        String sqlQuery = "SELECT OFFICE, CITY, EMPL_NUM, NAME, TITLE\n" +
                "FROM OFFICES JOIN SALESREPS ON\n" +
                "MGR = EMPL_NUM  AND TARGET > 60000;";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Office office = new Office();
                office.setOFFICE(rs.getInt("OFFICE"));
                office.setCITY(rs.getString("CITY"));
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                salesrep.setTITLE(rs.getString("TITLE"));
                office.setMGR(salesrep);
                offices.add(office);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return offices;
    }


    public Set<Office> getParentChildJoinCityFactOverPlan_p171() {
        Set<Office> offices = new LinkedHashSet<>(0);
        Statement statement = null;
        String sqlQuery = "SELECT OFFICE, CITY , SALES\n" +
                "FROM OFFICES\n" +
                "WHERE SALES > TARGET";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Office office = new Office();
                office.setOFFICE(rs.getInt("OFFICE"));
                office.setCITY(rs.getString("CITY"));
                office.setSALES(rs.getDouble("SALES"));
                offices.add(office);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return offices;
    }

    void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
