package org.demo.home_03.dto;

import lombok.Data;
import org.demo.home_03.model.Order;
import org.demo.home_03.model.ProductPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author dshvedchenko on 6/1/16.
 */
@Data
public class ProductDTO {
    private Integer mfrId;

    private Integer productId;

    private String description;

    private BigDecimal price;

    private Integer qtyOnHand;

    Set<Order> orders;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + getMfrId().hashCode();
        result = result * prime + getProductId().hashCode();

        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return  false;

        if (obj.getClass() != getClass()) return false;

        ProductDTO inputObjext = (ProductDTO) obj;

        if (inputObjext.getMfrId() == null) return false;

        if (inputObjext.getProductId() == null) return false;

        if (inputObjext.getMfrId() != this.getMfrId()
                || inputObjext.getProductId() != this.getProductId()) return false;
        return true;
    }
}
