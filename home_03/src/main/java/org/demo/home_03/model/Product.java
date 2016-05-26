package org.demo.home_03.model;

import lombok.Data;

/**
 * @author dshvedchenko on 5/26/16.
 */
@Data
public class Product {
    private String MFR_ID;
    private String PRODUCT_ID;
    private String DESCRIPTION;
    private Double PRICE;
    private Integer QTY_ON_HAND;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return  false;

        if (obj.getClass() != getClass()) return false;

        Product inputObjext = (Product) obj;

        if (inputObjext.getMFR_ID() == null) return false;

        if (inputObjext.getPRODUCT_ID() == null) return false;

        if (inputObjext.getMFR_ID() != this.getMFR_ID()
                || inputObjext.getPRODUCT_ID() != this.getPRODUCT_ID()) return false;


        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + getMFR_ID().hashCode();
        result = result * prime + getPRODUCT_ID().hashCode();

        return result;
    }
}
