package org.demo.home_03.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author dshvedchenko on 5/26/16.
 */
@Data
@Entity
@Table (name = "CUSTOMERS")
public class Customer {
    @Id
    private Integer custNum;

    @Column (name = "COMPANY")
    private String company;

    @ManyToOne
    @JoinColumn (name = "CUST_REP")
    private Salesrep custRep;

    @Column (name = "CREDIT_LIMIT")
    private BigDecimal creditLimit;

    @OneToMany (mappedBy = "CUST")
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
