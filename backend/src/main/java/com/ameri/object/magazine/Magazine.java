package com.ameri.object.magazine;

import com.ameri.object.user.manager.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Magazine {

    private final Integer magazineRecord;
    private final String editorName;
    private final String magazineName;
    private final Byte file;
    private final LocalDate publicationDate;
    private final String description;
    private final Category category;
    private final BigDecimal subscriptionCost;

    public Magazine(Integer magazineRecord, String editorName, String magazineName, Byte file, LocalDate publicationDate, String description, Category category, BigDecimal subscriptionCost) {
        this.magazineRecord = magazineRecord;
        this.editorName = editorName;
        this.magazineName = magazineName;
        this.file = file;
        this.publicationDate = publicationDate;
        this.description = description;
        this.category = category;
        this.subscriptionCost = subscriptionCost;
    }

    public Integer getMagazineRecord() {return magazineRecord;}

    public String getEditorName() {return editorName;}

    public String getMagazineName() {return magazineName;}

    public Byte getFile() {return file;}

    public LocalDate getPublicationDate() {return publicationDate;}

    public String getDescription() {return description;}

    public Category getCategory() {return category;}

    public BigDecimal getSubscriptionCost() {return subscriptionCost;}

}
