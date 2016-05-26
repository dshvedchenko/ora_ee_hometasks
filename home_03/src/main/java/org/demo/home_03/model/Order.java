package org.demo.home_03.model;

import lombok.Data;

import java.util.Date;
import java.util.DoubleSummaryStatistics;

/**
 * @author dshvedchenko on 5/26/16.
 */
@Data
public class Order {
    private Integer ORDER_NUM;
    private Date ORDER_DATE;
    private Customer CUST;
    private Salesrep REP;
    //private String MFR;
    private Product PRODUCT;
    private Integer QTY;
    private Double AMOUNT;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (obj.getClass() != this.getClass()) return false;

        Order inputObj = (Order) obj;

        if (this.getORDER_NUM() != inputObj.getORDER_NUM()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + getORDER_NUM();

        return result;
    }

}
