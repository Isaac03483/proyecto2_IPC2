package com.ameri.object.ads;

import java.math.BigDecimal;

public class AdsType {

    private final String typeName;
    private final BigDecimal dayCost;

    public AdsType(String typeName, BigDecimal dayCost) {
        this.typeName = typeName;
        this.dayCost = dayCost;
    }

    public String getTypeName() {return typeName;}

    public BigDecimal getDayCost() {return dayCost;}
}
