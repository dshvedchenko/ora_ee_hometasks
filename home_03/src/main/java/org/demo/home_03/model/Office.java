package org.demo.home_03.model;

import lombok.Data;

/**
 * @author dshvedchenko on 5/26/16.
 */
@Data
public class Office {
    private Integer OFFICE;
    private String CITY;
    private String REGION;
    private Integer MGR;
    private Double TARGET;
    private Double SALES;

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
