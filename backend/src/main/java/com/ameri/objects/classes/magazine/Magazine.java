package com.ameri.objects.classes.magazine;

import com.ameri.objects.classes.user.manager.Category;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;

public class Magazine {

    private final int magazineRecord;
    private final String editorName;
    private final String magazineName;
    private final Blob file;
    private final Date publicationDate;
    private final String description;
    private final Category category;
    private final BigDecimal subscriptionCost;

    public Magazine(int magazineRecord, String editorName, String magazineName, Blob file, Date publicationDate, String description, Category category, BigDecimal subscriptionCost) {
        this.magazineRecord = magazineRecord;
        this.editorName = editorName;
        this.magazineName = magazineName;
        this.file = file;
        this.publicationDate = publicationDate;
        this.description = description;
        this.category = category;
        this.subscriptionCost = subscriptionCost;
    }

    public int getMagazineRecord() {return magazineRecord;}

    public String getEditorName() {return editorName;}

    public String getMagazineName() {return magazineName;}

    public Blob getFile() {return file;}

    public Date getPublicationDate() {return publicationDate;}

    public String getDescription() {return description;}

    public Category getCategory() {return category;}

    public BigDecimal getSubscriptionCost() {return subscriptionCost;}

}
