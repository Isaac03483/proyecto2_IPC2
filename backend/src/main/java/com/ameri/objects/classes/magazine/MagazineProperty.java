package com.ameri.objects.classes.magazine;

import com.ameri.objects.enums.magazine.MagazineComment;
import com.ameri.objects.enums.magazine.MagazineLike;
import com.ameri.objects.enums.magazine.MagazineStatus;
import com.ameri.objects.enums.magazine.MagazineSubscription;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class MagazineProperty {

    private final int magazineRecord;
    private final Date acceptDate;
    private final MagazineStatus status;
    private final BigDecimal dayCost;
    private final Date updateDate;
    private final MagazineLike like;
    private final MagazineComment comment;
    private final MagazineSubscription subscription;

    public MagazineProperty(int magazineRecord, Date acceptDate, MagazineStatus status, BigDecimal dayCost, Date updateDate, MagazineLike like, MagazineComment comment, MagazineSubscription subscription) {
        this.magazineRecord = magazineRecord;
        this.acceptDate = acceptDate;
        this.status = status;
        this.dayCost = dayCost;
        this.updateDate = updateDate;
        this.like = like;
        this.comment = comment;
        this.subscription = subscription;
    }

    public int getMagazineRecord() {return magazineRecord;}

    public Date getAcceptDate() {return acceptDate;}

    public MagazineStatus getMagazineStatus(){return this.status;}

    public BigDecimal getDayCost() {return dayCost;}

    public Date getUpdateDate() {return updateDate;}

    public MagazineLike getLike() {return like;}

    public MagazineComment getComment() {return comment;}

    public MagazineSubscription getSubscription() {return subscription;}

}
