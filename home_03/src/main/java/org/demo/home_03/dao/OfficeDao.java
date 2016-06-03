package org.demo.home_03.dao;

import org.demo.home_03.dto.OfficeDTO;
import org.demo.home_03.model.Office;
import org.demo.home_03.model.Salesrep;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dshvedchenko on 5/27/16.
 */
public class OfficeDao {
    private Connection connection = null;
    private SessionHolder sessionHolder;

    OfficeDao(SessionHolder sessionHolder) {
        this.sessionHolder = sessionHolder;

    }

    public List<OfficeDTO> getQP119() {
        List<OfficeDTO> offices;
        offices = sessionHolder.obtainSession().createSQLQuery("SELECT CITY City,TARGET Target,SALES Sales FROM OFFICES")
                .setResultTransformer(Transformers.aliasToBean(OfficeDTO.class))
                .list();
        return offices;
    }

    public List<OfficeDTO> getQP1201() {
        List<OfficeDTO> offices = sessionHolder.obtainSession().createSQLQuery("SELECT CITY City" +
                ",TARGET Target," +
                "SALES Sales " +
                "FROM OFFICES " +
                "WHERE REGION = 'Eastern'")
                .setResultTransformer(Transformers.aliasToBean(OfficeDTO.class))
                .list();
        return offices;
    }

    public List<OfficeDTO> getQP1202() {
        List<OfficeDTO> offices = sessionHolder.obtainSession().createSQLQuery("SELECT CITY City" +
                ",TARGET Target," +
                "SALES Sales " +
                "FROM OFFICES " +
                "WHERE REGION = 'Eastern' " +
                "AND SALES > TARGET " +
                "ORDER BY CITY")
                .setResultTransformer(Transformers.aliasToBean(OfficeDTO.class))
                .list();
        return offices;
    }


    public List getQP126() {
        List offices = sessionHolder.obtainSession().createSQLQuery("SELECT CITY, REGION, (SALES - TARGET) debt FROM OFFICES")
                .addScalar("CITY", StandardBasicTypes.STRING)
                .addScalar("REGION", StandardBasicTypes.STRING)
                .addScalar("debt", StandardBasicTypes.BIG_DECIMAL)
                .list();
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
                office.setOffice(rs.getInt("OFFICE"));
                office.setCity(rs.getString("CITY"));
                Salesrep salesrep = new Salesrep();
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));
                salesrep.setTitle(rs.getString("TITLE"));
      //          office.setMGR(salesrep);
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
                office.setOffice(rs.getInt("OFFICE"));
                office.setCity(rs.getString("CITY"));
                Salesrep salesrep = new Salesrep();
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));
                salesrep.setTitle(rs.getString("TITLE"));
      //          office.setMGR(salesrep);
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
                office.setOffice(rs.getInt("OFFICE"));
                office.setCity(rs.getString("CITY"));
                office.setSales(rs.getBigDecimal("SALES"));
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
