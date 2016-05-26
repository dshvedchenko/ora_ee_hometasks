package org.demo.home_03.model;

import lombok.Data;

/**
 * @author dshvedchenko on 5/26/16.
 */
@Data
public class Customer {
    private Integer CUST_NUM;
    private String COMPANY;
    private Integer CUST_REP;
    private Double CREDIT_LIMIT;

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) return true;

        if ( obj == null ) return false;

        if ( this.getClass() != getClass() ) return false;
        Customer inputObj = (Customer) obj;

        if (this.getCUST_NUM() != inputObj.getCUST_NUM() ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + getCUST_NUM();

        return result;
    }
}
