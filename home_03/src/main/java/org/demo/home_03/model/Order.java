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
    private Integer CUST;
    private Integer REP;
    private String MFR;
    private String PRODUCT;
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

}
