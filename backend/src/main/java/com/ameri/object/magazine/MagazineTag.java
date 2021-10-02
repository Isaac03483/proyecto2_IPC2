package com.ameri.object.magazine;

public class MagazineTag {

    private final Integer magazineRecord;
    private final String tagName;

    public MagazineTag(Integer magazineRecord, String tagName) {
        this.magazineRecord = magazineRecord;
        this.tagName = tagName;
    }

    public Integer getMagazineRecord() {return magazineRecord;}

    public String getTagName() {return tagName;}
}
