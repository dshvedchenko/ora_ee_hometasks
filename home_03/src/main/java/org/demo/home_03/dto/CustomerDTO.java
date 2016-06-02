package org.demo.home_03.dto;

import lombok.Data;
import org.demo.home_03.model.Customer;
import org.demo.home_03.model.Order;
import org.demo.home_03.model.Salesrep;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * @author dshvedchenko on 6/1/16.
 */
@Data
public class CustomerDTO {
    private Integer custNum;

    private String company;

    private Salesrep custRep;

    private BigDecimal creditLimit;

    private Set<Order> orders ;

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) return true;

        if ( obj == null ) return false;

        if ( this.getClass() != getClass() ) return false;
        Customer inputObj = (Customer) obj;

        if (this.getCustNum() != inputObj.getCustNum() ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + getCustNum();

        return result;
    }
}
