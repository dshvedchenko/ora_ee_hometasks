package org.demo.home_03.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author dshvedchenko on 5/26/16.
 */
@Data
@Entity
@Table(name = "SALESREPS")
public class Salesrep {
    @Id
    @Column (name = "EMPL_NUM")
    private Integer EMPL_NUM;

    @Column (name = "NAME")
    private String NAME;

    @Column (name = "AGE")
    private Integer AGE;

    @Column (name = "TITLE")
    private String TITLE;

    @Column (name = "HIRE_DATE")
    private Date HIRE_DATE;

    @Column (name = "QUOTA")
    private Double QUOTA;

    @Column (name = "SALES")
    private Double SALES;

    @ManyToOne
    @JoinColumn (
            name = "MANAGER"
    )
    private Salesrep MANAGER;

    @ManyToOne
    @JoinColumn (
            name = "REP_OFFICE"
    )
    private Office REP_OFFICE;

    @OneToMany(mappedBy = "REP")
    private Set<Order> orders ;

    @OneToMany(mappedBy = "CUST_REP")
    private Set<Customer> customers ;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        Salesrep inputObject = (Salesrep) obj;
        if (this.getEMPL_NUM() != inputObject.getEMPL_NUM()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + getEMPL_NUM();

        return result;
    }
}
