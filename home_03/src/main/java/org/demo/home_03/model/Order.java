package org.demo.home_03.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.DoubleSummaryStatistics;

/**
 * @author dshvedchenko on 5/26/16.
 */
@Data
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @Column (name ="ORDER_NUM")
    private Integer ORDER_NUM;

    @Column (name = "ORDER_DATE")
    private Date ORDER_DATE;

    @ManyToOne
    @JoinColumn (
            name = "CUST"
    )
    private Customer CUST;

    @ManyToOne
    @JoinColumn (
            name = "REP"
    )
    private Salesrep REP;

    @ManyToOne
    @JoinColumns(
            { @JoinColumn (name = "MFR", referencedColumnName = "MFR_ID")
         , @JoinColumn (name = "PRODUCT", referencedColumnName = "PRODUCT_ID") }
    )
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
