package com.ameri.objects.Beans;

import com.ameri.objects.classes.user.editor.EditorAccount;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class OtherMagazineBeans {

    private int magazineRecord;
    private BigDecimal dayCost;
    private BigDecimal totalDayCost;
    private BigDecimal gananciaTotal;
    private List<EditorAccount> subscriptionList;

    {
        this.gananciaTotal = new BigDecimal(0);
    }

    public OtherMagazineBeans(int magazineRecord, BigDecimal dayCost) {
        this.magazineRecord = magazineRecord;
        this.dayCost = dayCost;
    }

    public int getMagazineRecord() {
        return magazineRecord;
    }

    public void setMagazineRecord(int magazineRecord) {
        this.magazineRecord = magazineRecord;
    }

    public BigDecimal getDayCost() {
        return dayCost;
    }

    public void setDayCost(BigDecimal dayCost) {
        this.dayCost = dayCost;
    }

    public BigDecimal getTotalDayCost() {
        return totalDayCost;
    }

    public void setTotalDayCost(BigDecimal totalDayCost) {
        this.totalDayCost = totalDayCost;
    }

    public BigDecimal getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(LocalDate start, LocalDate end) {

        Period period = Period.between(start, end);
        int days = period.getDays() + (period.getMonths()*30) + (period.getYears()*365);

        for(EditorAccount subscription: subscriptionList){
            gananciaTotal = BigDecimal.valueOf(gananciaTotal.doubleValue() + subscription.getDescuento().doubleValue());
        }

        totalDayCost = BigDecimal.valueOf((dayCost.doubleValue() * days));

        gananciaTotal = BigDecimal.valueOf(gananciaTotal.doubleValue() - totalDayCost.doubleValue());

    }

    public List<EditorAccount> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<EditorAccount> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }
}
