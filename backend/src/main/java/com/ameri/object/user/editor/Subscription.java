package com.ameri.object.user.editor;

import com.ameri.enums.PaymentEnum;
import com.ameri.enums.SubscriptionLike;
import com.ameri.enums.SubscriptionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Subscription {

    private final Integer subscriptionRecord;
    private final String subscriberName;
    private final Integer magazineRecord;
    private final BigDecimal totalPay;
    private final PaymentEnum paymentInterval;
    private final LocalDate recordDate;
    private final SubscriptionStatus subscriptionStatus;
    private final SubscriptionLike subscriptionLike;

    public Subscription(Integer subscriptionRecord, String subscriberName, Integer magazineRecord, BigDecimal totalPay, PaymentEnum paymentInterval, LocalDate recordDate, SubscriptionStatus subscriptionStatus, SubscriptionLike subscriptionLike) {
        this.subscriptionRecord = subscriptionRecord;
        this.subscriberName = subscriberName;
        this.magazineRecord = magazineRecord;
        this.totalPay = totalPay;
        this.paymentInterval = paymentInterval;
        this.recordDate = recordDate;
        this.subscriptionStatus = subscriptionStatus;
        this.subscriptionLike = subscriptionLike;
    }

    public Integer getSubscriptionRecord() {
        return subscriptionRecord;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public Integer getMagazineRecord() {
        return magazineRecord;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public PaymentEnum getPaymentInterval() {
        return paymentInterval;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public SubscriptionStatus getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public SubscriptionLike getSubscriptionLike() {
        return subscriptionLike;
    }
}
