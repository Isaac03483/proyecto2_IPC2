package com.ameri.objects.classes.ads;

import com.ameri.objects.enums.ad.AdStatus;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Ad {

    private final int adRecord;
    private final AdsType adsType;
    private final String adName;
    private final String clientName;
    private final String adText;
    private final String adContent;
    private final int views;
    private final BigDecimal totalCost;
    private final AdStatus adsStatus;
    private final String url;
    private final LocalDate startDate;
    private final LocalDate endDate;


    public Ad(AdsType adsType, String adName, String clientName, String adText, String adContent, int views, BigDecimal totalCost, AdStatus adsStatus, String url, LocalDate startDate, LocalDate endDate) {
        this.adRecord = 0;
        this.adsType = adsType;
        this.adName = adName;
        this.clientName = clientName;
        this.adText = adText;
        this.adContent = adContent;
        this.views = views;
        this.totalCost = totalCost;
        this.adsStatus = adsStatus;
        this.url = url;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Ad(int adRecord, AdsType adsType, String adName, String clientName, String adText, String adContent, int views, BigDecimal totalCost, AdStatus adsStatus, String url, LocalDate startDate, LocalDate endDate) {
        this.adRecord = adRecord;
        this.adsType = adsType;
        this.adName = adName;
        this.clientName = clientName;
        this.adText = adText;
        this.adContent = adContent;
        this.views = views;
        this.totalCost = totalCost;
        this.adsStatus = adsStatus;
        this.url = url;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getAdRecord() {return adRecord;}

    public AdsType getAdsType() {return adsType;}

    public String getAdName() {return adName;}

    public String getClientName() {return clientName;}

    public String getAdText(){return this.adText;}

    public String getAdContent() {return adContent;}

    public int getViews() {return views;}

    public BigDecimal getTotalCost() {return totalCost;}

    public AdStatus getAdsStatus() {return adsStatus;}

    public String getUrl() {return url;}

    public Date getStartDate() {return Date.valueOf(startDate);}

    public Date getEndDate() {return Date.valueOf(endDate);}

}
