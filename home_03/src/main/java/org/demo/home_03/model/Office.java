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
@Table( name = "OFFICES")
public class Office {

    @Id
    private Integer OFFICE;

    @Column (name = "CITY")
    private String CITY;

    @Column (name = "REGION")
    private String REGION;

    @Column (name = "TARGET")
    private Double TARGET;

    @Column (name = "SALES")
    private Double SALES;

    @OneToOne
    @JoinColumn (
            name = "MGR"
    )
    private Salesrep MGR;

    @OneToMany(mappedBy = "REP_OFFICE")
    private Set<Salesrep> Salesreps;

    @Override
    public boolean equals(Object obj) {

        if ( this == obj) return true;

        if (obj == null) return false;

        if (obj.getClass() != getClass()) return  false;

        Office inputObject = (Office) obj;

        if (this.getOFFICE() != inputObject.getOFFICE()) return false;

        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + getOFFICE();

        return result;
    }

}
