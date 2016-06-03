package org.demo.home_03.dao;

import org.demo.home_03.dto.OrderDTO;
import org.demo.home_03.model.*;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dshvedchenko on 5/26/16.
 */
public class OrderDao {

    private Connection connection;

    private SessionHolder sessionHolder;

    OrderDao(SessionHolder sessionHolder) {
        this.sessionHolder = sessionHolder;
    }


    public List<Order> getSimpleP135() {
        List<Order> orders = sessionHolder.obtainSession().createSQLQuery("SELECT ORDER_NUM, ORDER_DATE, MFR, PRODUCT, AMOUNT, CUST, QTY, REP " +
                "FROM ORDERS " +
                "WHERE ORDER_DATE BETWEEN '2007-10-01' and '2007-12-31'")
                .addEntity(Order.class)
                .list();
        return orders;
    }

    public List<OrderDTO> getSimple1362() {
        List<OrderDTO> orders = sessionHolder.obtainSession().createSQLQuery("SELECT ORDER_NUM OrderNum, AMOUNT Amount " +
                "FROM ORDERS " +
                "WHERE AMOUNT BETWEEN 20000 and 29999")
                .setResultTransformer(Transformers.aliasToBean(OrderDTO.class))
                .list();
        return orders;
    }

    public List<OrderDTO> getP138() {
        List<OrderDTO> orders = sessionHolder.obtainSession().createSQLQuery("SELECT ORDER_NUM OrderNum, REP Rep, AMOUNT Amount " +
                "FROM ORDERS " )
                .setResultTransformer(Transformers.aliasToBean(OrderDTO.class))
                .list();
        return orders;
    }


    public List<Order> getSimple_p158() {
        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , COMPANY , CREDIT_LIMIT, CUST_NUM\n" +
        "FROM ORDERS, CUSTOMERS\n" +
                "WHERE CUST = CUST_NUM";
        List<Order> orders = sessionHolder.obtainSession().createCriteria(Order.class, "o")
                .createCriteria("cust","c")
                .add(Restrictions.eqProperty("o.cust", "c.custNum"))
                .list();

        return orders;
    }

//    public Set<Order> getParentChildJoinsMultiKey_p163() {
//        Set<Order> orders = new LinkedHashSet<>(0);
//        Statement statement = null;
//        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , MFR_ID, PRODUCT_ID, DESCRIPTION\n" +
//                "FROM ORDERS, PRODUCTS\n" +
//                "WHERE MFR = MFR_ID\n" +
//                "AND PRODUCT = PRODUCT_ID";
//
//
//        try {
//            statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery (sqlQuery);
//            if (rs.next()) {
//                Order order = new Order();
//                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
//                order.setAMOUNT( rs.getDouble("AMOUNT") );
//                Product product = new Product();
//                product.setMFR_ID(rs.getString("MFR_ID"));
//                product.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
//                product.setDESCRIPTION(rs.getString("DESCRIPTION"));
//                order.setPRODUCT(product);
//                orders.add(order);
//            }
//
//            rs.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeStatement(statement);
//        }
//
//        return orders;
//    }

//    public Set<Order> getParentChildJoinsMultiKeyAlter_p163() {
//        Set<Order> orders = new LinkedHashSet<>(0);
//        Statement statement = null;
//        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , MFR_ID, PRODUCT_ID, DESCRIPTION\n" +
//                "FROM ORDERS JOIN PRODUCTS ON\n" +
//                "MFR = MFR_ID\n" +
//                "AND PRODUCT = PRODUCT_ID";
//
//
//        try {
//            statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery (sqlQuery);
//            if (rs.next()) {
//                Order order = new Order();
//                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
//                order.setAMOUNT( rs.getDouble("AMOUNT") );
//                Product product = new Product();
//                product.setMFR_ID(rs.getString("MFR_ID"));
//                product.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
//                product.setDESCRIPTION(rs.getString("DESCRIPTION"));
//                order.setPRODUCT(product);
//                orders.add(order);
//            }
//
//            rs.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeStatement(statement);
//        }
//
//        return orders;
//    }

//    public Set<Order> getParentChildNaturalJoin_p164() {
//        Set<Order> orders = new LinkedHashSet<>(0);
//        Statement statement = null;
//        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , MFR_ID, PRODUCT_ID, DESCRIPTION FROM ORDERS NATURAL JOIN PRODUCTS ;";
//
//
//        try {
//            statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery (sqlQuery);
//            if (rs.next()) {
//                Order order = new Order();
//                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
//                order.setAMOUNT( rs.getDouble("AMOUNT") );
//                Product product = new Product();
//                product.setMFR_ID(rs.getString("MFR_ID"));
//                product.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
//                product.setDESCRIPTION(rs.getString("DESCRIPTION"));
//                order.setPRODUCT(product);
//                orders.add(order);
//            }
//
//            rs.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeStatement(statement);
//        }
//
//        return orders;
//    }

//    public Set<Order> getParentChildJoinMulti_p164() {
//        Set<Order> orders = new LinkedHashSet<>(0);
//        Statement statement = null;
//        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , DESCRIPTION, MFR_ID, PRODUCT_ID FROM ORDERS JOIN PRODUCTS " +
//                "ON ORDERS.MFR = PRODUCTS.MFR_ID " +
//                "AND ORDERS.PRODUCT = PRODUCTS.PRODUCT_ID;";
//
//
//        try {
//            statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery (sqlQuery);
//            if (rs.next()) {
//                Order order = new Order();
//                order.setORDER_NUM( rs.getInt("ORDER_NUM"));
//                order.setAMOUNT( rs.getDouble("AMOUNT") );
//                Product product = new Product();
//                product.setMFR_ID(rs.getString("MFR_ID"));
//                product.setPRODUCT_ID(rs.getString("PRODUCT_ID"));
//                product.setDESCRIPTION(rs.getString("DESCRIPTION"));
//                order.setPRODUCT(product);
//                orders.add(order);
//            }
//
//            rs.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeStatement(statement);
//        }
//
//        return orders;
//    }

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
                order.setOrderNum( rs.getInt("ORDER_NUM"));
                order.setAmount( rs.getBigDecimal("AMOUNT") );
                Customer customer = new Customer();
                customer.setCustNum(rs.getInt("CUST_NUM"));
                customer.setCompany(rs.getString("COMPANY"));
                order.setCust(customer);
                Salesrep salesrep = new Salesrep();
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));
                order.setRep(salesrep);
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
                order.setOrderNum( rs.getInt("ORDER_NUM"));
                order.setAmount( rs.getBigDecimal("AMOUNT") );
                Customer customer = new Customer();
                customer.setCustNum(rs.getInt("CUST_NUM"));
                customer.setCompany(rs.getString("COMPANY"));
                order.setCust(customer);
                Salesrep salesrep = new Salesrep();
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));
                order.setRep(salesrep);
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
                order.setOrderNum( rs.getInt("ORDER_NUM"));
                order.setAmount( rs.getBigDecimal("AMOUNT") );
                Customer customer = new Customer();
                customer.setCustNum(rs.getInt("CUST_NUM"));
                customer.setCompany(rs.getString("COMPANY"));
                order.setCust(customer);
                Salesrep salesrep = new Salesrep();
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));
                order.setRep(salesrep);
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
                order.setOrderNum( rs.getInt("ORDER_NUM"));
                order.setAmount( rs.getBigDecimal("AMOUNT") );

                Customer customer = new Customer();
                customer.setCustNum(rs.getInt("CUST_NUM"));
                customer.setCompany(rs.getString("COMPANY"));
                order.setCust(customer);

                Salesrep salesrep = new Salesrep();
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));

                Office office = new Office();
                office.setOffice(rs.getInt("OFFICE"));
                office.setCity(rs.getString("CITY"));

                salesrep.setRepOffice(office);
                order.setRep(salesrep);
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

    public Set<Order> getParentChildJoinOrderHire_p169() {
        Set<Order> orders = new LinkedHashSet<>(0);
        Statement statement = null;
        String sqlQuery = "SELECT ORDER_NUM, AMOUNT , ORDER_DATE , EMPL_NUM, NAME\n" +
                "FROM ORDERS , SALESREPS\n" +
                "WHERE ORDER_DATE = HIRE_DATE ;";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery (sqlQuery);
            if (rs.next()) {
                Order order = new Order();
                order.setOrderNum( rs.getInt("ORDER_NUM"));
                order.setAmount( rs.getBigDecimal("AMOUNT") );
                order.setOrderDate(rs.getDate("ORDER_DATE"));


                Salesrep salesrep = new Salesrep();
                salesrep.setEmplNum(rs.getInt("EMPL_NUM"));
                salesrep.setName(rs.getString("NAME"));

                order.setRep(salesrep);
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
