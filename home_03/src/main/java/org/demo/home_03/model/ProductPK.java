package org.demo.home_03.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author dshvedchenko on 5/30/16.
 */

@Embeddable
@Data
public class ProductPK implements Serializable {
    protected Integer MFR_ID;
    protected Integer PRODUCT_ID;

    public ProductPK () {}

    public ProductPK(Integer MFR_ID, Integer PRODUCT_ID) {
        this.MFR_ID = MFR_ID;
        this.PRODUCT_ID = PRODUCT_ID;
    }
}
