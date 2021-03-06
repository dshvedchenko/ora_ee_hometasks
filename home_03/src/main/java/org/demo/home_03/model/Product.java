package org.demo.home_03.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author dshvedchenko on 5/26/16.
 */
@Data
@Entity
@Table( name = "PRODUCTS")
public class Product implements Serializable {
    @EmbeddedId
    ProductPK productPK;

    @Column (name = "DESCRIPTION")
    private String description;

    @Column (name = "PRICE")
    private BigDecimal price;

    @Column (name = "QTY_ON_HAND")
    private Integer qtyOnHand;

    @OneToMany (mappedBy = "product")
    Set<Order> orders = new LinkedHashSet<>(0);

    @Override
    public boolean equals(Object obj) {
        return productPK.equals(obj);
    }

    @Override
    public int hashCode() {
        return productPK.hashCode();
    }
}
