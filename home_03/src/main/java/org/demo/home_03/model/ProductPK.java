package org.demo.home_03.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author dshvedchenko on 5/30/16.
 */

@Embeddable
@Data
public class ProductPK implements Serializable {
    @Column (name ="MFR_ID", columnDefinition = "CHAR(3)")
    protected String mfrId;
    @Column (name = "PRODUCT_ID", columnDefinition = "CHAR(5)")
    protected String productId;

    public ProductPK () {}

    public ProductPK(String mfrId, String productId) {
        this.mfrId = mfrId;
        this.productId = productId;
    }
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

        Product inputObjext = (Product) obj;

        if (inputObjext.productPK.getMfrId() == null) return false;

        if (inputObjext.productPK.getProductId() == null) return false;

        if (inputObjext.productPK.getMfrId() != this.getMfrId()
                || inputObjext.productPK.getProductId() != this.getProductId()) return false;
        return true;
    }


}
