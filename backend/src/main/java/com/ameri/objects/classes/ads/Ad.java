package com.ameri.objects.classes.ads;

import com.ameri.objects.enums.ad.AdStatus;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Ad {

    private final int adRecord;
    private final AdType adType;
    private final String adName;
    private final String clientName;
    private final String adText;
    private final String adContent;
    private final int views;
    private final BigDecimal totalCost;
    private final AdStatus adStatus;
    private final String url;
    private final Date startDate;
    private final Date endDate;

    public Ad(int adRecord, AdType adsType, String adName, String clientName, String adText, String adContent, int views, BigDecimal totalCost, AdStatus adsStatus, String url, Date startDate, Date endDate) {
        this.adRecord = adRecord;
        this.adType = adsType;
        this.adName = adName;
        this.clientName = clientName;
        this.adText = adText;
        this.adContent = adContent;
        this.views = views;
        this.totalCost = totalCost;
        this.adStatus = adsStatus;
        this.url = url;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getAdRecord() {return adRecord;}

    public AdType getAdType() {return adType;}

    public String getAdName() {return adName;}

    public String getClientName() {return clientName;}

    public String getAdText(){return this.adText;}

    public String getAdContent() {return adContent;}

    public int getViews() {return views;}

    public BigDecimal getTotalCost() {return totalCost;}

    public AdStatus getAdStatus() {return adStatus;}

    public String getUrl() {return url;}

    public Date getStartDate() {return startDate;}

    public Date getEndDate() {return endDate;}

}
