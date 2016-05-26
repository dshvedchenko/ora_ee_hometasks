package org.demo.home_03.model;

import lombok.Data;

import java.util.Date;

/**
 * @author dshvedchenko on 5/26/16.
 */
@Data
public class Salesrep {
    private Integer EMPL_NUM;
    private String NAME;
    private Integer AGE;
    private Integer REP_OFFICE;
    private String TITLE;
    private Date HIRE_DATE;
    private Integer MANAGER;
    private Double QUOTA;
    private Double SALES;

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
