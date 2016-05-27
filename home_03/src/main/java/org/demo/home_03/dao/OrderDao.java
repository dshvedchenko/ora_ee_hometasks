package org.demo.home_03.dao;

import org.demo.home_03.model.Customer;
import org.demo.home_03.model.Order;
import org.demo.home_03.model.Product;
import org.demo.home_03.model.Salesrep;

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
