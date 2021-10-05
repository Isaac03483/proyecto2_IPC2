package com.ameri.objects.classes.user.editor;

import com.ameri.objects.enums.user.editor.PaymentEnum;
import com.ameri.objects.enums.user.editor.SubscriptionLike;
import com.ameri.objects.enums.user.editor.SubscriptionStatus;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Subscription {

    private final int subscriptionRecord;
    private final String subscriberName;
    private final int magazineRecord;
    private final BigDecimal totalPay;
    private final PaymentEnum paymentInterval;
    private final LocalDate recordDate;
    private final SubscriptionStatus subscriptionStatus;
    private final SubscriptionLike subscriptionLike;

    public Subscription(int subscriptionRecord, String subscriberName, int magazineRecord, BigDecimal totalPay, PaymentEnum paymentInterval, LocalDate recordDate, SubscriptionStatus subscriptionStatus, SubscriptionLike subscriptionLike) {
        this.subscriptionRecord = subscriptionRecord;
        this.subscriberName = subscriberName;
        this.magazineRecord = magazineRecord;
        this.totalPay = totalPay;
        this.paymentInterval = paymentInterval;
        this.recordDate = recordDate;
        this.subscriptionStatus = subscriptionStatus;
        this.subscriptionLike = subscriptionLike;
    }

    public int getSubscriptionRecord() {return subscriptionRecord;}

    public String getSubscriberName() {return subscriberName;}

    public int getMagazineRecord() {return magazineRecord;}

    public BigDecimal getTotalPay() {return totalPay;}

    public PaymentEnum getPaymentInterval() {return paymentInterval;}

    public Date getRecordDate() {return Date.valueOf(recordDate);}

    public SubscriptionStatus getSubscriptionStatus() {return subscriptionStatus;}

    public SubscriptionLike getSubscriptionLike() {return subscriptionLike;}
}
