package com.ameri.object.user.editor;


import java.math.BigDecimal;
import java.time.LocalDate;

public class EditorAccount {

    private final Integer accountRecord;
    private final String editorName;
    private final String subscriberName;
    private final String journalName;
    private final BigDecimal totalPay;
    private final BigDecimal descuento;
    private final BigDecimal ganancia;
    private final LocalDate fechaPago;

    public EditorAccount(Integer accountRecord, String editorName, String subscriberName, String journalName, BigDecimal totalPay, BigDecimal descuento, BigDecimal ganancia, LocalDate fechaPago) {
        this.accountRecord = accountRecord;
        this.editorName = editorName;
        this.subscriberName = subscriberName;
        this.journalName = journalName;
        this.totalPay = totalPay;
        this.descuento = descuento;
        this.ganancia = ganancia;
        this.fechaPago = fechaPago;
    }

    public Integer getAccountRecord() {return accountRecord;}

    public String getEditorName() {return editorName;}

    public String getSubscriberName() {return subscriberName;}

    public String getJournalName() {return journalName;}

    public BigDecimal getTotalPay() {return totalPay;}

    public BigDecimal getDescuento() {return descuento;}

    public BigDecimal getGanancia() {return ganancia;}

    public LocalDate getFechaPago() {return fechaPago;}
}
