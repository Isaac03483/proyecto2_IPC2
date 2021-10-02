package com.ameri.object.ads;

import com.ameri.enums.AdsStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Ad {

    private final Integer adRecord;
    private final AdsType adsType;
    private final String adName;
    private final String clientName;
    private final String adContent;
    private final Integer views;
    private final BigDecimal totalCost;
    private final AdsStatus adsStatus;
    private final String url;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Ad(Integer adRecord, AdsType adsType, String adName, String clientName, String adContent, Integer views, BigDecimal totalCost, AdsStatus adsStatus, String url, LocalDate startDate, LocalDate endDate) {
        this.adRecord = adRecord;
        this.adsType = adsType;
        this.adName = adName;
        this.clientName = clientName;
        this.adContent = adContent;
        this.views = views;
        this.totalCost = totalCost;
        this.adsStatus = adsStatus;
        this.url = url;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getAdRecord() {return adRecord;}

    public AdsType getAdsType() {return adsType;}

    public String getAdName() {return adName;}

    public String getClientName() {return clientName;}

    public String getAdContent() {return adContent;}

    public Integer getViews() {return views;}

    public BigDecimal getTotalCost() {return totalCost;}

    public AdsStatus getAdsStatus() {return adsStatus;}

    public String getUrl() {return url;}

    public LocalDate getStartDate() {return startDate;}

    public LocalDate getEndDate() {return endDate;}

}
