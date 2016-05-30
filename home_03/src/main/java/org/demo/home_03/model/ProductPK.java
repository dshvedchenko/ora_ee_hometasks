package org.demo.home_03.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author dshvedchenko on 5/30/16.
 */

@Embeddable
@Data
public class ProductPK implements Serializable {
    @Column (name ="MFR_ID", columnDefinition = "CHAR(3)")
    protected String MFR_ID;
    @Column (name = "PRODUCT_ID", columnDefinition = "CHAR(5)")
    protected String PRODUCT_ID;

    public ProductPK () {}

    public ProductPK(String MFR_ID, String PRODUCT_ID) {
        this.MFR_ID = MFR_ID;
        this.PRODUCT_ID = PRODUCT_ID;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + getMFR_ID().hashCode();
        result = result * prime + getPRODUCT_ID().hashCode();

        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return  false;

        if (obj.getClass() != getClass()) return false;

        Product inputObjext = (Product) obj;

        if (inputObjext.productPK.getMFR_ID() == null) return false;

        if (inputObjext.productPK.getPRODUCT_ID() == null) return false;

        if (inputObjext.productPK.getMFR_ID() != this.getMFR_ID()
                || inputObjext.productPK.getPRODUCT_ID() != this.getPRODUCT_ID()) return false;
        return true;
    }


}
