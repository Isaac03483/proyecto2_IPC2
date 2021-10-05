package com.ameri.objects.classes.user.editor;


import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class EditorAccount {

    private final int accountRecord;
    private final String editorName;
    private final String subscriberName;
    private final String magazineName;
    private final BigDecimal totalPay;
    private final BigDecimal descuento;
    private final BigDecimal ganancia;
    private final LocalDate fechaPago;

    public EditorAccount(int accountRecord, String editorName, String subscriberName, String magazineName, BigDecimal totalPay, BigDecimal descuento, BigDecimal ganancia, LocalDate fechaPago) {
        this.accountRecord = accountRecord;
        this.editorName = editorName;
        this.subscriberName = subscriberName;
        this.magazineName = magazineName;
        this.totalPay = totalPay;
        this.descuento = descuento;
        this.ganancia = ganancia;
        this.fechaPago = fechaPago;
    }

    public int getAccountRecord() {return accountRecord;}

    public String getEditorName() {return editorName;}

    public String getSubscriberName() {return subscriberName;}

    public String getMagazineName() {return magazineName;}

    public BigDecimal getTotalPay() {return totalPay;}

    public BigDecimal getDescuento() {return descuento;}

    public BigDecimal getGanancia() {return ganancia;}

    public Date getPayDate() {return Date.valueOf(fechaPago);}
}
