package com.ameri.objects.beans.adminBeans;

import java.util.List;

public class GananciaBeans {

    private double total;
    private List<OtherMagazineBeans> otherMagazineBeans;

    {

        total = 0;
    }

    public GananciaBeans( List<OtherMagazineBeans> otherMagazineBeans) {
        this.otherMagazineBeans = otherMagazineBeans;
    }

    public void updateTotal(){
        for(OtherMagazineBeans other: otherMagazineBeans){
            total += other.getGananciaTotal().doubleValue();
            System.out.println(other.getGananciaTotal().doubleValue());
        }
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OtherMagazineBeans> getOtherMagazineBeans() {
        return otherMagazineBeans;
    }

    public void setOtherMagazineBeans(List<OtherMagazineBeans> otherMagazineBeans) {
        this.otherMagazineBeans = otherMagazineBeans;
    }

    public double getTotal() {
        return total;
    }
}
