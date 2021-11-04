package com.ameri.objects.classes.user.editor;

import com.ameri.objects.enums.user.editor.PaymentEnum;
import com.ameri.objects.enums.user.editor.SubscriptionLike;
import com.ameri.objects.enums.user.editor.SubscriptionStatus;

import java.io.StringBufferInputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Subscription {

    private final int subscriptionRecord;
    private final String subscriberName;
    private final int magazineRecord;
    private final String magazineName;
    private final BigDecimal totalPay;
    private final PaymentEnum paymentInterval;
    private final String recordDate;
    private final String endDate;
    private final SubscriptionStatus subscriptionStatus;
    private final SubscriptionLike subscriptionLike;
    private int likes;

    {
        this.likes = 0;
    }

    public Subscription(int subscriptionRecord, String subscriberName, int magazineRecord, String magazineName, BigDecimal totalPay, PaymentEnum paymentInterval, String recordDate, String endDate, SubscriptionStatus subscriptionStatus, SubscriptionLike subscriptionLike) {
        this.subscriptionRecord = subscriptionRecord;
        this.subscriberName = subscriberName;
        this.magazineRecord = magazineRecord;
        this.magazineName = magazineName;
        this.totalPay = totalPay;
        this.paymentInterval = paymentInterval;
        this.recordDate = recordDate;
        this.endDate = endDate;
        this.subscriptionStatus = subscriptionStatus;
        this.subscriptionLike = subscriptionLike;
    }

    public Subscription(){
        this.subscriptionRecord = 0;
        this.subscriptionLike = null;
        this.subscriberName = null;
        this.magazineRecord = 0;
        this.magazineName = null;
        this.totalPay = null;
        this.paymentInterval = null;
        this.recordDate = null;
        this.endDate = null;
        this.subscriptionStatus = null;
    }
    public Subscription(int subscriptionRecord, SubscriptionLike subscriptionLike) {
        this.subscriptionRecord = subscriptionRecord;
        this.subscriptionLike = subscriptionLike;
        this.subscriberName = null;
        this.magazineRecord = 0;
        this.magazineName = null;
        this.totalPay = null;
        this.paymentInterval = null;
        this.recordDate = null;
        this.endDate = null;
        this.subscriptionStatus = null;
    }

    public Subscription(int subscriptionRecord) {
        this.subscriptionRecord = subscriptionRecord;
        this.subscriptionLike = null;
        this.subscriberName = null;
        this.magazineRecord = 0;
        this.magazineName = null;
        this.totalPay = null;
        this.paymentInterval = null;
        this.recordDate = null;
        this.endDate = null;
        this.subscriptionStatus = null;
    }

    public Subscription(int subscriptionRecord, BigDecimal totalPay, PaymentEnum paymentInterval, String endDate, SubscriptionStatus subscriptionStatus) {
        this.subscriptionRecord = subscriptionRecord;
        this.subscriptionLike = null;
        this.subscriberName = null;
        this.magazineRecord = 0;
        this.magazineName = null;
        this.totalPay = totalPay;
        this.paymentInterval = paymentInterval;
        this.recordDate = null;
        this.endDate = endDate;
        this.subscriptionStatus = subscriptionStatus;
    }



    public int getSubscriptionRecord() {return subscriptionRecord;}

    public String getSubscriberName() {return subscriberName;}

    public int getMagazineRecord() {return magazineRecord;}

    public String getMagazineName(){return this.magazineName;}

    public BigDecimal getTotalPay() {return totalPay;}

    public PaymentEnum getPaymentInterval() {return paymentInterval;}

    public Date getRecordDate() {
        assert recordDate != null;
        return Date.valueOf(recordDate);}

    public Date getEndDate() {
        assert endDate != null;
        return Date.valueOf(endDate);}

    public SubscriptionStatus getSubscriptionStatus() {return subscriptionStatus;}

    public SubscriptionLike getSubscriptionLike() {return subscriptionLike;}

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
