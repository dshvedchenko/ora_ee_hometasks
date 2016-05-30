package org.demo.home_03.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
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
    private String DESCRIPTION;

    @Column (name = "PRICE")
    private Double PRICE;

    @Column (name = "QTY_ON_HAND")
    private Integer QTY_ON_HAND;

    @OneToMany (mappedBy = "PRODUCT")
    Set<Order> Orders = new LinkedHashSet<>(0);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return  false;

        if (obj.getClass() != getClass()) return false;

        Product inputObjext = (Product) obj;

        if (inputObjext.productPK.getMFR_ID() == null) return false;

        if (inputObjext.productPK.getPRODUCT_ID() == null) return false;

        if (inputObjext.productPK.getMFR_ID() != this.productPK.getMFR_ID()
                || inputObjext.productPK.getPRODUCT_ID() != this.productPK.getPRODUCT_ID()) return false;


        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + productPK.getMFR_ID().hashCode();
        result = result * prime + productPK.getPRODUCT_ID().hashCode();

        return result;
    }
}
