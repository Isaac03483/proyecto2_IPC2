package com.ameri.objects.classes.user.manager;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Impuesto {

    private final int registroImpuesto;
    private final BigDecimal percentage;
    private final LocalDate updateDate;

    public Impuesto(int registroImpuesto, BigDecimal percentage, LocalDate updateDate) {
        this.registroImpuesto = registroImpuesto;
        this.percentage = percentage;
        this.updateDate = updateDate;
    }

    public int getRegistroImpuesto() {return registroImpuesto;}

    public BigDecimal getPercentage() {return percentage;}

    public Date getUpdateDate() {return Date.valueOf(updateDate);}
}
