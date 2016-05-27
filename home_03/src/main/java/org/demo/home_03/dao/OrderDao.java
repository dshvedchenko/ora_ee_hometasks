package org.demo.home_03.dao;

import org.demo.home_03.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author dshvedchenko on 5/26/16.
 */
public class OrderDao {
    private final Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    public Set<Order> getSimple_p158() {
        Set<Order> orders = new LinkedHashSet<>(0);
        Statement statement = null;
        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , COMPANY , CREDIT_LIMIT, CUST_NUM\n" +
        "FROM ORDERS, CUSTOMERS\n" +
                "WHERE CUST = CUST_NUM";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Order order = new Order();
                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
                order.setAMOUNT( rs.getDouble("AMOUNT") );
                Customer customer = new Customer();
                customer.setCOMPANY( rs.getString("COMPANY"));
                customer.setCREDIT_LIMIT( rs.getDouble("CREDIT_LIMIT"));
                customer.setCUST_NUM(rs.getInt("CUST_NUM"));
                order.setCUST(customer);

                orders.add(order);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return orders;
    }

    public Set<Order> getParentChildJoinsMultiKey_p163() {
        Set<Order> orders = new LinkedHashSet<>(0);
        Statement statement = null;
        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , MFR_ID, PRODUCT_ID, DESCRIPTION\n" +
                "FROM ORDERS, PRODUCTS\n" +
                "WHERE MFR = MFR_ID\n" +
                "AND PRODUCT = PRODUCT_ID";


        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Order order = new Order();
                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
                order.setAMOUNT( rs.getDouble("AMOUNT") );
                Product product = new Product();
                product.setMFR_ID(rs.getString("MFR_ID"));
                product.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
                product.setDESCRIPTION(rs.getString("DESCRIPTION"));
                order.setPRODUCT(product);
                orders.add(order);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return orders;
    }

    public Set<Order> getParentChildJoinsMultiKeyAlter_p163() {
        Set<Order> orders = new LinkedHashSet<>(0);
        Statement statement = null;
        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , MFR_ID, PRODUCT_ID, DESCRIPTION\n" +
                "FROM ORDERS JOIN PRODUCTS ON\n" +
                "MFR = MFR_ID\n" +
                "AND PRODUCT = PRODUCT_ID";


        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Order order = new Order();
                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
                order.setAMOUNT( rs.getDouble("AMOUNT") );
                Product product = new Product();
                product.setMFR_ID(rs.getString("MFR_ID"));
                product.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
                product.setDESCRIPTION(rs.getString("DESCRIPTION"));
                order.setPRODUCT(product);
                orders.add(order);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return orders;
    }

    public Set<Order> getParentChildNaturalJoin_p164() {
        Set<Order> orders = new LinkedHashSet<>(0);
        Statement statement = null;
        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , MFR_ID, PRODUCT_ID, DESCRIPTION FROM ORDERS NATURAL JOIN PRODUCTS ;";


        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Order order = new Order();
                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
                order.setAMOUNT( rs.getDouble("AMOUNT") );
                Product product = new Product();
                product.setMFR_ID(rs.getString("MFR_ID"));
                product.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
                product.setDESCRIPTION(rs.getString("DESCRIPTION"));
                order.setPRODUCT(product);
                orders.add(order);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return orders;
    }

    public Set<Order> getParentChildJoinMulti_p164() {
        Set<Order> orders = new LinkedHashSet<>(0);
        Statement statement = null;
        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , DESCRIPTION, MFR_ID, PRODUCT_ID FROM ORDERS JOIN PRODUCTS " +
                "ON ORDERS.MFR = PRODUCTS.MFR_ID " +
                "AND ORDERS.PRODUCT = PRODUCTS.PRODUCT_ID;";


        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Order order = new Order();
                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
                order.setAMOUNT( rs.getDouble("AMOUNT") );
                Product product = new Product();
                product.setMFR_ID(rs.getString("MFR_ID"));
                product.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
                product.setDESCRIPTION(rs.getString("DESCRIPTION"));
                order.setPRODUCT(product);
                orders.add(order);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return orders;
    }

    public Set<Order> getParentChildJoin3Table_p165() {
        Set<Order> orders = new LinkedHashSet<>(0);
        Statement statement = null;
        String sqlQuery = "SELECT ORDER_NUM, AMOUNT, COMPANY, CUST_NUM, EMPL_NUM, NAME\n" +
                "FROM ORDERS , CUSTOMERS , SALESREPS\n" +
                "WHERE CUST = CUST_NUM\n" +
                "AND REP = EMPL_NUM\n" +
                "AND AMOUNT > 25000.00;";


        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Order order = new Order();
                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
                order.setAMOUNT( rs.getDouble("AMOUNT") );
                Customer customer = new Customer();
                customer.setCUST_NUM(rs.getInt("CUST_NUM"));
                customer.setCOMPANY(rs.getString("COMPANY"));
                order.setCUST(customer);
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                order.setREP(salesrep);
                orders.add(order);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return orders;
    }

    public Set<Order> getParentChildJoin3TableAlt_p165() {
        Set<Order> orders = new LinkedHashSet<>(0);
        Statement statement = null;
        String sqlQuery = "SELECT ORDER_NUM, AMOUNT, COMPANY, CUST_NUM, EMPL_NUM, NAME\n" +
                "FROM ORDERS JOIN CUSTOMERS ON CUST = CUST_NUM \n " +
                "JOIN SALESREPS ON REP = EMPL_NUM\n" +
                "WHERE AMOUNT > 25000.00;";


        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Order order = new Order();
                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
                order.setAMOUNT( rs.getDouble("AMOUNT") );
                Customer customer = new Customer();
                customer.setCUST_NUM(rs.getInt("CUST_NUM"));
                customer.setCOMPANY(rs.getString("COMPANY"));
                order.setCUST(customer);
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                order.setREP(salesrep);
                orders.add(order);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return orders;
    }

    public Set<Order> getParentChildJoin3TableCustRep_p166() {
        Set<Order> orders = new LinkedHashSet<>(0);
        Statement statement = null;
        String sqlQuery = "SELECT ORDER_NUM, AMOUNT, COMPANY, CUST_NUM, EMPL_NUM, NAME\n" +
                "FROM ORDERS JOIN CUSTOMERS ON CUST = CUST_NUM \n " +
                "JOIN SALESREPS ON CUST_REP = EMPL_NUM\n" +
                "WHERE AMOUNT > 25000.00;";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Order order = new Order();
                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
                order.setAMOUNT( rs.getDouble("AMOUNT") );
                Customer customer = new Customer();
                customer.setCUST_NUM(rs.getInt("CUST_NUM"));
                customer.setCOMPANY(rs.getString("COMPANY"));
                order.setCUST(customer);
                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));
                order.setREP(salesrep);
                orders.add(order);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return orders;
    }

    public Set<Order> getParentChildJoin3TableCustRepOffice_p167() {
        Set<Order> orders = new LinkedHashSet<>(0);
        Statement statement = null;
        String sqlQuery = "SELECT ORDER_NUM, AMOUNT, COMPANY, CUST_NUM, EMPL_NUM, NAME, OFFICE, CITY\n" +
                "FROM ORDERS , CUSTOMERS , SALESREPS, OFFICES\n" +
                "WHERE CUST = CUST_NUM\n" +
                "AND CUST_REP = EMPL_NUM\n" +
                "AND REP_OFFICE = OFFICE\n" +
                "AND AMOUNT > 25000.00;\n";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Order order = new Order();
                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
                order.setAMOUNT( rs.getDouble("AMOUNT") );

                Customer customer = new Customer();
                customer.setCUST_NUM(rs.getInt("CUST_NUM"));
                customer.setCOMPANY(rs.getString("COMPANY"));
                order.setCUST(customer);

                Salesrep salesrep = new Salesrep();
                salesrep.setEMPL_NUM(rs.getInt("EMPL_NUM"));
                salesrep.setNAME(rs.getString("NAME"));

                Office office = new Office();
                office.setOFFICE(rs.getInt("OFFICE"));
                office.setCITY(rs.getString("CITY"));

                salesrep.setREP_OFFICE(office);
                order.setREP(salesrep);
                orders.add(order);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return orders;
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
