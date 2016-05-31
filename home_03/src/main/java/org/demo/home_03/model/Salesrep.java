package org.demo.home_03.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
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
    private Integer emplNum;

    @Column (name = "NAME")
    private String name;

    @Column (name = "AGE")
    private Integer age;

    @Column (name = "TITLE")
    private String title;

    @Column (name = "HIRE_DATE")
    private Date hireDate;

    @Column (name = "QUOTA")
    private BigDecimal quota;

    @Column (name = "SALES")
    private BigDecimal sales;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (
            name = "MANAGER"
            ,referencedColumnName = "EMPL_NUM"
            , foreignKey = @ForeignKey (name = "SALESREPS_ibfk_1")
    )
    private Salesrep manager;

    @ManyToOne (fetch = FetchType.LAZY, targetEntity = Office.class)
    @JoinColumn (
            name = "REP_OFFICE"
            , referencedColumnName = "OFFICE"
            , foreignKey = @ForeignKey (name = "SALESREPS_ibfk_2")
            , nullable = true
    )
    private Office repOffice;

    @OneToMany(mappedBy = "rep", fetch = FetchType.LAZY)
    private Set<Order> orders ;

    @OneToMany(mappedBy = "custRep", fetch = FetchType.LAZY)
    private Set<Customer> customers ;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        Salesrep inputObject = (Salesrep) obj;
        if (this.getEmplNum() != inputObject.getEmplNum()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + getEmplNum();

        return result;
    }
}
