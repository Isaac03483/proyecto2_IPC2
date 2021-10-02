package com.ameri.object.magazine;

import com.ameri.enums.MagazineComment;
import com.ameri.enums.MagazineLike;
import com.ameri.enums.MagazineSubscription;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MagazineProperty {

    private final Integer magazineRecord;
    private final LocalDate acceptDate;
    private final BigDecimal dayCost;
    private final LocalDate updateDate;
    private final MagazineLike like;
    private final MagazineComment comment;
    private final MagazineSubscription subscription;

    public MagazineProperty(Integer magazineRecord, LocalDate acceptDate, BigDecimal dayCost, LocalDate updateDate, MagazineLike like, MagazineComment comment, MagazineSubscription subscription) {
        this.magazineRecord = magazineRecord;
        this.acceptDate = acceptDate;
        this.dayCost = dayCost;
        this.updateDate = updateDate;
        this.like = like;
        this.comment = comment;
        this.subscription = subscription;
    }

    public Integer getMagazineRecord() {return magazineRecord;}

    public LocalDate getAcceptDate() {return acceptDate;}

    public BigDecimal getDayCost() {return dayCost;}

    public LocalDate getUpdateDate() {return updateDate;}

    public MagazineLike getLike() {return like;}

    public MagazineComment getComment() {return comment;}

    public MagazineSubscription getSubscription() {return subscription;}

}
