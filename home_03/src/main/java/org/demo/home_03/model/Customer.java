package org.demo.home_03.model;

import lombok.Data;

import javax.persistence.*;
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
    private Integer CUST_NUM;

    @Column (name = "COMPANY")
    private String COMPANY;

    @ManyToOne
    @JoinColumn (name = "CUST_REP")
    private Salesrep CUST_REP;

    @Column (name = "CREDIT_LIMIT")
    private Double CREDIT_LIMIT;

    @OneToMany (mappedBy = "CUST")
    private Set<Order> orders ;

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
