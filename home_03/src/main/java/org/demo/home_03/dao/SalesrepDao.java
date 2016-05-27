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

    public Set<Salesrep> getParentChildJoinNotEq_p170() {
        Set<Salesrep> salesReps = new LinkedHashSet<>();
        Statement statement = null;
        String sqlQuery = "SELECT EMPL_NUM, NAME , QUOTA,OFFICE, CITY, TARGET\n" +
                "FROM SALESREPS , OFFICES\n" +
                "WHERE QUOTA > TARGET ";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                salesrep.setQUOTA(rs.getDouble("QUOTA"));

                Office office = new Office();
                office.setOFFICE(rs.getInt("OFFICE"));
                office.setCITY(rs.getString("CITY"));
                office.setTARGET(rs.getDouble("TARGET"));

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

    public Set<Salesrep> getParentChildJoinEmplOv350000_p172() {
        Set<Salesrep> salesReps = new LinkedHashSet<>();
        Statement statement = null;
        String sqlQuery = "SELECT NAME , SALES, EMPL_NUM\n" +
                "FROM SALESREPS\n" +
                "WHERE SALES > 350000";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                salesrep.setSALES(rs.getDouble("SALES"));
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

    public Set<Salesrep> getParentChildJoinEmplOffices_p172() {
        Set<Salesrep> salesReps = new LinkedHashSet<>();
        Statement statement = null;
        String sqlQuery = "SELECT EMPL_NUM, NAME , SALESREPS.SALES, CITY, OFFICE\n" +
                "FROM SALESREPS, OFFICES\n" +
                "WHERE REP_OFFICE = OFFICE";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                salesrep.setSALES(rs.getDouble("SALESREPS.SALES"));
                Office office = new Office();
                office.setOFFICE(rs.getInt("OFFICE"));
                office.setCITY(rs.getString("CITY"));
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

    public Set<Salesrep> getParentChildJoinAllCols_p173() {
        Set<Salesrep> salesReps = new LinkedHashSet<>();
        Statement statement = null;
        String sqlQuery = "SELECT *\n" +
                "FROM SALESREPS, OFFICES\n" +
                "WHERE REP_OFFICE = OFFICE";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                salesrep.setSALES(rs.getDouble("SALESREPS.SALES"));
                Office office = new Office();
                office.setOFFICE(rs.getInt("OFFICE"));
                office.setCITY(rs.getString("CITY"));
                office.setSALES(rs.getDouble("OFFICES.SALES"));
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

    public Set<Salesrep> queryParentChildJoinEmplAllWOffice_p173() {
        Set<Salesrep> salesReps = new LinkedHashSet<>();
        Statement statement = null;
        String sqlQuery = "SELECT SALESREPS.*, OFFICE, CITY, REGION\n" +
                "FROM SALESREPS, OFFICES\n" +
                "WHERE REP_OFFICE = OFFICE";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                salesrep.setSALES(rs.getDouble("SALESREPS.SALES"));
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


    public Set<Salesrep> queryParentChildSelfJoinEmplMgr_p175() {
        Set<Salesrep> salesReps = new LinkedHashSet<>();
        Statement statement = null;
        String sqlQuery = "SELECT EMPS.EMPL_NUM, EMPS.NAME, MGRS.EMPL_NUM, MGRS.NAME\n" +
                "FROM SALESREPS EMPS, SALESREPS MGRS\n" +
                "WHERE EMPS.MANAGER = MGRS.EMPL_NUM";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPS.EMPL_NUM"));
                salesrep.setNAME(rs.getString("EMPS.NAME"));

                Salesrep manager = new Salesrep();
                manager.setEMPL_NUM(rs.getInt("MGRS.EMPL_NUM"));
                manager.setNAME(rs.getString("MGRS.NAME"));
                salesrep.setMANAGER(manager);
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

    public Set<Salesrep> queryParentChildSelfJoinEmplMgrObeAlias_p175() {
        Set<Salesrep> salesReps = new LinkedHashSet<>();
        Statement statement = null;
        String sqlQuery = "SELECT SALESREPS.EMPL_NUM, SALESREPS.NAME, MGRS.EMPL_NUM, MGRS.NAME\n" +
                "FROM SALESREPS, SALESREPS MGRS\n" +
                "WHERE SALESREPS.MANAGER = MGRS.EMPL_NUM";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));

                Salesrep manager = new Salesrep();
                manager.setEMPL_NUM(rs.getInt("MGRS.EMPL_NUM"));
                manager.setNAME(rs.getString("MGRS.NAME"));
                salesrep.setMANAGER(manager);
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

    public Set<Salesrep> queryParentChildSelfJoinEmplPlanOverMgrPlan_p175() {
        Set<Salesrep> salesReps = new LinkedHashSet<>();
        Statement statement = null;
        String sqlQuery = "SELECT SALESREPS.EMPL_NUM,SALESREPS.NAME, SALESREPS.QUOTA,MGRS.EMPL_NUM,  MGRS.QUOTA\n" +
                "FROM SALESREPS, SALESREPS MGRS\n" +
                "WHERE SALESREPS.MANAGER = MGRS.EMPL_NUM\n" +
                "AND SALESREPS.QUOTA > MGRS.QUOTA";


        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("SALESREPS.EMPL_NUM"));
                salesrep.setNAME(rs.getString("SALESREPS.NAME"));
                salesrep.setQUOTA(rs.getDouble("SALESREPS.QUOTA"));

                Salesrep manager = new Salesrep();
                manager.setEMPL_NUM(rs.getInt("MGRS.EMPL_NUM"));
                manager.setQUOTA(rs.getDouble("MGRS.QUOTA"));
                salesrep.setMANAGER(manager);
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

    public Set<Salesrep> queryParentChildSelfJoinEmplMgrDiffOffices_p176() {
        Set<Salesrep> salesReps = new LinkedHashSet<>();
        Statement statement = null;
        String sqlQuery = "SELECT " +
                "EMPS.EMPL_NUM" +
                ", EMPS.NAME" +
                ", EMP_OFFICE.OFFICE" +
                ", EMP_OFFICE.CITY" +
                ", MGRS.EMPL_NUM" +
                ", MGRS.NAME" +
                ", MGRS_OFFICE.OFFICE,  MGRS_OFFICE.CITY\n" +
                "FROM SALESREPS EMPS, SALESREPS MGRS, OFFICES EMP_OFFICE, OFFICES MGRS_OFFICE\n" +
                "WHERE EMPS.REP_OFFICE = EMP_OFFICE.OFFICE\n" +
                "AND MGRS.REP_OFFICE = MGRS_OFFICE.OFFICE\n" +
                "AND EMPS.MANAGER = MGRS.EMPL_NUM\n" +
                "AND EMPS.REP_OFFICE <> MGRS.REP_OFFICE";


        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPS.EMPL_NUM"));
                salesrep.setNAME(rs.getString("EMPS.NAME"));

                Office emplOffice = new Office();
                emplOffice.setOFFICE(rs.getInt("EMP_OFFICE.OFFICE"));
                emplOffice.setCITY(rs.getString("EMP_OFFICE.CITY"));
                salesrep.setREP_OFFICE(emplOffice);

                Salesrep manager = new Salesrep();
                manager.setEMPL_NUM(rs.getInt("MGRS.EMPL_NUM"));
                manager.setNAME(rs.getString("MGRS.NAME"));

                Office mgrOffice = new Office();
                mgrOffice.setOFFICE(rs.getInt("MGRS_OFFICE.OFFICE"));
                mgrOffice.setCITY(rs.getString("MGRS_OFFICE.CITY"));
                manager.setREP_OFFICE(mgrOffice);

                salesrep.setMANAGER(manager);
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
