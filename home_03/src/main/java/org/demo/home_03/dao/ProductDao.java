package org.demo.home_03.dao;

import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import java.sql.Connection;
import java.util.List;

/**
 * @author dshvedchenko on 6/2/16.
 */
public class ProductDao {
    private Connection connection = null;
    private SessionHolder sessionHolder;

    ProductDao(SessionHolder sessionHolder) {
        this.sessionHolder = sessionHolder;

    }

    public List getQP1262() {
        List salesreps = sessionHolder.obtainSession().createSQLQuery("SELECT MFR_ID, PRODUCT_ID, DESCRIPTION, (QTY_ON_HAND * PRICE) totals " +
             "FROM PRODUCTS")
             .addScalar("MFR_ID", StandardBasicTypes.STRING)
             .addScalar("PRODUCT_ID", StandardBasicTypes.STRING)
             .addScalar("DESCRIPTION", StandardBasicTypes.STRING)
               .addScalar("totals", StandardBasicTypes.BIG_DECIMAL)
             .list();
       return salesreps;
    }

}
