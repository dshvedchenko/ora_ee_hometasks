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
    ProductPK productPK;

    private String description;

    private BigDecimal price;

    private Integer qtyOnHand;

    Set<Order> orders;

    public boolean equals(Object obj) {
        return productPK.equals(obj);
    }

    @Override
    public int hashCode() {
        return productPK.hashCode();
    }
}
