package com.ameri.object.user.editor;

import com.ameri.object.enums.PaymentEnum;
import com.ameri.object.enums.SubscriptionLike;
import com.ameri.object.enums.SubscriptionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Subscription {

    private Integer subscriptionRecord;
    private String subscriberName;
    private Integer journalRecord;
    private BigDecimal totalPay;
    private PaymentEnum paymentInterval;
    private LocalDate recordDate;
    private SubscriptionStatus subscriptionStatus;
    private SubscriptionLike subscriptionLike;

    public Subscription(Integer subscriptionRecord, String subscriberName, Integer journalRecord, BigDecimal totalPay, PaymentEnum paymentInterval, LocalDate recordDate, SubscriptionStatus subscriptionStatus, SubscriptionLike subscriptionLike) {
        this.subscriptionRecord = subscriptionRecord;
        this.subscriberName = subscriberName;
        this.journalRecord = journalRecord;
        this.totalPay = totalPay;
        this.paymentInterval = paymentInterval;
        this.recordDate = recordDate;
        this.subscriptionStatus = subscriptionStatus;
        this.subscriptionLike = subscriptionLike;
    }

    public Integer getSubscriptionRecord() {
        return subscriptionRecord;
    }

    public void setSubscriptionRecord(Integer subscriptionRecord) {
        this.subscriptionRecord = subscriptionRecord;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public Integer getJournalRecord() {
        return journalRecord;
    }

    public void setJournalRecord(Integer journalRecord) {
        this.journalRecord = journalRecord;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(BigDecimal totalPay) {
        this.totalPay = totalPay;
    }

    public PaymentEnum getPaymentInterval() {
        return paymentInterval;
    }

    public void setPaymentInterval(PaymentEnum paymentInterval) {
        this.paymentInterval = paymentInterval;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public SubscriptionStatus getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public SubscriptionLike getSubscriptionLike() {
        return subscriptionLike;
    }

    public void setSubscriptionLike(SubscriptionLike subscriptionLike) {
        this.subscriptionLike = subscriptionLike;
    }
}
