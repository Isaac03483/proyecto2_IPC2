package com.ameri.objects.classes.ads;

import java.math.BigDecimal;

public class AdType {

    private final String typeName;
    private final BigDecimal dayCost;

    public AdType(String typeName, BigDecimal dayCost) {
        this.typeName = typeName;
        this.dayCost = dayCost;
    }

    public String getTypeName() {return typeName;}

    public BigDecimal getDayCost() {return dayCost;}
}
