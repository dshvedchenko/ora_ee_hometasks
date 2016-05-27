package org.demo.home_03.dao;

import org.demo.home_03.model.Office;
import org.demo.home_03.model.Salesrep;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author dshvedchenko on 5/26/16.
 */
public class SalesrepDao {
    private final Connection connection;

    public SalesrepDao(Connection connection) {
        this.connection = connection;
    }

    public Set<Salesrep> getParentChild_p159() {
        Set<Salesrep> salesReps = new LinkedHashSet<>();
        Statement statement = null;
        String sqlQuery = "SELECT EMPL_NUM, NAME, OFFICE, CITY, REGION\n" +
                "FROM SALESREPS , OFFICES\n" +
                "WHERE REP_OFFICE = OFFICE;";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                Office office = new Office();
                office.setOFFICE(rs.getInt("OFFICE"));
                office.setCITY(rs.getString("CITY"));
                office.setREGION(rs.getString("REGION"));
                salesrep.setREP_OFFICE(office);
                salesReps.add(salesrep);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return salesReps;
    }

    public Set<Salesrep> getParentChild_p161() {
        Set<Salesrep> salesReps = new LinkedHashSet<>();
        Statement statement = null;
        String sqlQuery = "SELECT EMPL_NUM, NAME, OFFICE, CITY, REGION\n" +
                "FROM SALESREPS JOIN OFFICES ON\n" +
                "REP_OFFICE = OFFICE;";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                Office office = new Office();
                office.setOFFICE(rs.getInt("OFFICE"));
                office.setCITY(rs.getString("CITY"));
                office.setREGION(rs.getString("REGION"));
                salesrep.setREP_OFFICE(office);
                salesReps.add(salesrep);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return salesReps;
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
