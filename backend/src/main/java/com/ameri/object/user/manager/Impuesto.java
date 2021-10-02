package com.ameri.object.user.manager;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Impuesto {

    private final Integer registroImpuesto;
    private final BigDecimal percentage;
    private final LocalDate updateDate;

    public Impuesto(Integer registroImpuesto, BigDecimal percentage, LocalDate updateDate) {
        this.registroImpuesto = registroImpuesto;
        this.percentage = percentage;
        this.updateDate = updateDate;
    }

    public Integer getRegistroImpuesto() {return registroImpuesto;}

    public BigDecimal getPercentage() {return percentage;}

    public LocalDate getUpdateDate() {return updateDate;}
}
