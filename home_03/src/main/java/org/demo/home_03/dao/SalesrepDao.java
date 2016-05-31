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
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));
                Office office = new Office();
                office.setOffice(rs.getInt("OFFICE"));
                office.setCity(rs.getString("CITY"));
                office.setRegion(rs.getString("REGION"));
                salesrep.setRepOffice(office);
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
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));
                Office office = new Office();
                office.setOffice(rs.getInt("OFFICE"));
                office.setCity(rs.getString("CITY"));
                office.setRegion(rs.getString("REGION"));
                salesrep.setRepOffice(office);
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
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));
                salesrep.setQuota(rs.getBigDecimal("QUOTA"));

                Office office = new Office();
                office.setOffice(rs.getInt("OFFICE"));
                office.setCity(rs.getString("CITY"));
                office.setTarget(rs.getBigDecimal("TARGET"));

                salesrep.setRepOffice(office);
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
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));
                salesrep.setSales(rs.getBigDecimal("SALES"));
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
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));
                salesrep.setSales(rs.getBigDecimal("SALESREPS.SALES"));
                Office office = new Office();
                office.setOffice(rs.getInt("OFFICE"));
                office.setCity(rs.getString("CITY"));
                salesrep.setRepOffice(office);
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
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));
                salesrep.setSales(rs.getBigDecimal("SALESREPS.SALES"));
                Office office = new Office();
                office.setOffice(rs.getInt("OFFICE"));
                office.setCity(rs.getString("CITY"));
                office.setSales(rs.getBigDecimal("OFFICES.SALES"));
                salesrep.setRepOffice(office);
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
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));
                salesrep.setSales(rs.getBigDecimal("SALESREPS.SALES"));
                Office office = new Office();
                office.setOffice(rs.getInt("OFFICE"));
                office.setCity(rs.getString("CITY"));
                office.setRegion(rs.getString("REGION"));
                salesrep.setRepOffice(office);
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
                salesrep.setEmplNum(rs.getInt("EMPS.EMPL_NUM"));
                salesrep.setName(rs.getString("EMPS.NAME"));

                Salesrep manager = new Salesrep();
                manager.setEmplNum(rs.getInt("MGRS.EMPL_NUM"));
                manager.setName(rs.getString("MGRS.NAME"));
                salesrep.setManager(manager);
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
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));

                Salesrep manager = new Salesrep();
                manager.setEmplNum(rs.getInt("MGRS.EMPL_NUM"));
                manager.setName(rs.getString("MGRS.NAME"));
                salesrep.setManager(manager);
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
                salesrep.setEmplNum(rs.getInt("SALESREPS.EMPL_NUM"));
                salesrep.setName(rs.getString("SALESREPS.NAME"));
                salesrep.setQuota(rs.getBigDecimal("SALESREPS.QUOTA"));

                Salesrep manager = new Salesrep();
                manager.setEmplNum(rs.getInt("MGRS.EMPL_NUM"));
                manager.setQuota(rs.getBigDecimal("MGRS.QUOTA"));
                salesrep.setManager(manager);
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
                salesrep.setEmplNum(rs.getInt("EMPS.EMPL_NUM"));
                salesrep.setName(rs.getString("EMPS.NAME"));

                Office emplOffice = new Office();
                emplOffice.setOffice(rs.getInt("EMP_OFFICE.OFFICE"));
                emplOffice.setCity(rs.getString("EMP_OFFICE.CITY"));
                salesrep.setRepOffice(emplOffice);

                Salesrep manager = new Salesrep();
                manager.setEmplNum(rs.getInt("MGRS.EMPL_NUM"));
                manager.setName(rs.getString("MGRS.NAME"));

                Office mgrOffice = new Office();
                mgrOffice.setOffice(rs.getInt("MGRS_OFFICE.OFFICE"));
                mgrOffice.setCity(rs.getString("MGRS_OFFICE.CITY"));
                manager.setRepOffice(mgrOffice);

                salesrep.setManager(manager);
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

    public Set<Salesrep> queryParentChildNamexCity_p179() {
        Set<Salesrep> salesReps = new LinkedHashSet<>();
        Statement statement = null;
        String sqlQuery = "SELECT EMPL_NUM, NAME, OFFICE, CITY FROM SALESREPS, OFFICES";


        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Salesrep salesrep = new Salesrep();
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));

                Office emplOffice = new Office();
                emplOffice.setOffice(rs.getInt("OFFICES.OFFICE"));
                emplOffice.setCity(rs.getString("OFFICES.CITY"));
                salesrep.setRepOffice(emplOffice);

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
