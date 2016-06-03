package org.demo.home_03.dto;

import lombok.Data;
import org.demo.home_03.model.Customer;
import org.demo.home_03.model.Order;
import org.demo.home_03.model.Product;
import org.demo.home_03.model.Salesrep;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dshvedchenko on 6/1/16.
 */
@Data
public class OrderDTO {
    private Integer orderNum;

    private Date orderDate;

    private Integer cust;

    private Integer rep;

    private String mfr;

    private String product;

    private Integer qty;
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
}
