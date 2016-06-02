package org.demo.home_03.dto;

import lombok.Data;
import org.demo.home_03.model.Office;
import org.demo.home_03.model.Salesrep;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author dshvedchenko on 6/1/16.
 */
@Data
public class OfficeDTO {
    private Integer office;

    private String city;

    private String region;

    private Salesrep manager;

    private BigDecimal target;

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
}
