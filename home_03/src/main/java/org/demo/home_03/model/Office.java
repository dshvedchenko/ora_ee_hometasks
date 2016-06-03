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
@Table( name = "OFFICES")
public class Office {

    @Id
    @Column (name = "OFFICE")
    private Integer office;

    @Column (name = "CITY")
    private String city;

    @Column (name = "REGION")
    private String region;


    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn (
            name = "MGR", referencedColumnName = "EMPL_NUM", nullable = false
    )
    private Salesrep manager;

    @Column (name = "TARGET")
    private BigDecimal target;

    @Column (name = "SALES")
    private BigDecimal sales;

    @Override
    public boolean equals(Object obj) {

        if ( this == obj) return true;

        if (obj == null) return false;

        if (obj.getClass() != getClass()) return  false;

        Office inputObject = (Office) obj;

        if (this.getOffice() != inputObject.getOffice()) return false;

        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + getOffice();

        return result;
    }

    @Override
    public String toString() {
        return String.format( "Office  Num : %d\r\nCity : %s\r\nRegion : %s\r\nManager %s", getOffice(), getCity(), getRegion(), getManager().getName() );
    }

}
