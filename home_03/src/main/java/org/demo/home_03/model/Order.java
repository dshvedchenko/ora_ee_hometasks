package org.demo.home_03.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dshvedchenko on 5/26/16.
 */
@Data
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @Column(name = "ORDER_NUM")
    private Integer orderNum;

    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "CUST"
    )
    private Customer cust;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "REP"
    )
    private Salesrep rep;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumns(
            {@JoinColumn(name = "MFR", referencedColumnName = "MFR_ID")
                    , @JoinColumn(name = "PRODUCT", referencedColumnName = "PRODUCT_ID")}
    )
    private Product product;

    @Column( name = "QTY")
    private Integer qty;

    @Column (name = "AMOUNT")
    private BigDecimal amount;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (obj.getClass() != this.getClass()) return false;

        Order inputObj = (Order) obj;

        if (this.getOrderNum() != inputObj.getOrderNum()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + getOrderNum();

        return result;
    }


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public synchronized String toString() {
        return String.format("ORDER NUM : %d\r\nORDER_DATE %s\r\nCustomer : %s", getOrderNum(), sdf.format(getOrderDate()), getCust().getCompany());
    }

}
