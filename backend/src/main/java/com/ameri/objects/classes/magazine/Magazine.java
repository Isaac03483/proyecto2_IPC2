package com.ameri.objects.classes.magazine;

import com.ameri.objects.classes.user.manager.Category;
import com.ameri.objects.enums.magazine.MagazineComment;
import com.ameri.objects.enums.magazine.MagazineLike;
import com.ameri.objects.enums.magazine.MagazineStatus;
import com.ameri.objects.enums.magazine.MagazineSubscription;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;

public class Magazine {

    private final int magazineRecord;
    private final String editorName;
    private final String magazineName;
    private final String file;
    private final String publicationDate;
    private final String description;
    private final Category category;
    private final BigDecimal subscriptionCost;
    private final String acceptDate;
    private final MagazineStatus status;
    private final BigDecimal dayCost;
    private final String updateDate;
    private final MagazineLike like;
    private final MagazineComment comment;
    private final MagazineSubscription subscription;

    public Magazine(int magazineRecord, String editorName, String magazineName,String file, String publicationDate, String description, Category category, BigDecimal subscriptionCost, MagazineStatus status,String acceptDate, BigDecimal dayCost, String updateDate, MagazineLike like, MagazineComment comment, MagazineSubscription subscription) {
        this.magazineRecord = magazineRecord;
        this.editorName = editorName;
        this.magazineName = magazineName;
        this.file = file;
        this.publicationDate = publicationDate;
        this.description = description;
        this.category = category;
        this.subscriptionCost = subscriptionCost;
        this.acceptDate = acceptDate;
        this.status = status;
        this.dayCost = dayCost;
        this.updateDate = updateDate;
        this.like = like;
        this.comment = comment;
        this.subscription = subscription;
    }

    public Magazine(String editorName, String magazineName, String file, String publicationDate, String description, Category category, BigDecimal subscriptionCost, MagazineStatus status, MagazineLike like, MagazineComment comment, MagazineSubscription subscription) {
        this.magazineRecord = 0;
        this.editorName = editorName;
        this.magazineName = magazineName;
        this.file = file;
        this.publicationDate = publicationDate;
        this.description = description;
        this.category = category;
        this.acceptDate = null;
        this.updateDate = null;
        this.dayCost = null;
        this.subscriptionCost = subscriptionCost;
        this.status = status;
        this.like = like;
        this.comment = comment;
        this.subscription = subscription;
    }

    public Magazine(int magazineRecord) {
        this.magazineRecord = magazineRecord;
        this.editorName = null;
        this.magazineName = null;
        this.file = null;
        this.publicationDate = null;
        this.description = null;
        this.category = null;
        this.acceptDate = null;
        this.updateDate = null;
        this.dayCost = null;
        this.subscriptionCost = null;
        this.status = null;
        this.like = null;
        this.comment = null;
        this.subscription = null;
    }

    public Magazine(String magazineName, Category category, String editorName) {
        this.magazineRecord = 0;
        this.editorName = editorName;
        this.magazineName = magazineName;
        this.file = null;
        this.publicationDate = null;
        this.description = null;
        this.category = category;
        this.acceptDate = null;
        this.updateDate = null;
        this.dayCost = null;
        this.subscriptionCost = null;
        this.status = null;
        this.like = null;
        this.comment = null;
        this.subscription = null;
    }

    public Magazine(String magazineName, String editorName) {
        this.magazineRecord = 0;
        this.editorName = editorName;
        this.magazineName = magazineName;
        this.file = null;
        this.publicationDate = null;
        this.description = null;
        this.category = null;
        this.acceptDate = null;
        this.updateDate = null;
        this.dayCost = null;
        this.subscriptionCost = null;
        this.status = null;
        this.like = null;
        this.comment = null;
        this.subscription = null;
    }

    public Magazine(int magazineRecord, String editorName, String magazineName, String publicationDate, String description, Category category, BigDecimal subscriptionCost, String acceptDate, MagazineStatus status, BigDecimal dayCost, String updateDate, MagazineLike like, MagazineComment comment, MagazineSubscription subscription) {
        this.magazineRecord = magazineRecord;
        this.editorName = editorName;
        this.magazineName = magazineName;
        this.file = null;
        this.publicationDate = publicationDate;
        this.description = description;
        this.category = category;
        this.subscriptionCost = subscriptionCost;
        this.acceptDate = acceptDate;
        this.status = status;
        this.dayCost = dayCost;
        this.updateDate = updateDate;
        this.like = like;
        this.comment = comment;
        this.subscription = subscription;
    }

    public int getMagazineRecord() {return magazineRecord;}

    public String getEditorName() {return editorName;}

    public String getMagazineName() {return magazineName;}

    public String getFile() {return file;}

    public Date getPublicationDate() {
        assert publicationDate != null;
        return Date.valueOf(publicationDate);}

    public String getDescription() {return description;}

    public Category getCategory() {return category;}

    public BigDecimal getSubscriptionCost() {return subscriptionCost;}

    public Date getAcceptDate() {
        assert acceptDate != null;
        return Date.valueOf(acceptDate);}

    public MagazineStatus getStatus() {return status;}

    public BigDecimal getDayCost() {return dayCost;}

    public Date getUpdateDate() {
        assert updateDate != null;
        return Date.valueOf(updateDate);}

    public MagazineLike getLike() {return like;}

    public MagazineComment getComment() {return comment;}

    public MagazineSubscription getSubscription() {return subscription;}
}
