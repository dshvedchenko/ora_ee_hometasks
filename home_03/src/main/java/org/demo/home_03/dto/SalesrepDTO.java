package org.demo.home_03.dto;

import lombok.Data;
import org.demo.home_03.model.Customer;
import org.demo.home_03.model.Office;
import org.demo.home_03.model.Order;
import org.demo.home_03.model.Salesrep;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * @author dshvedchenko on 6/1/16.
 */
@Data
public class SalesrepDTO {
    private Integer emplNum;

    private String name;

    private Integer age;

    private String title;

    private Date hireDate;

    private BigDecimal quota;

    private BigDecimal sales;

    private SalesrepDTO manager;

    private OfficeDTO repOffice;

    private Set<OrderDTO> orders ;

    private Set<CustomerDTO> customers ;


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
