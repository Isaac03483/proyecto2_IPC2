package com.ameri.objects.classes.magazine;

public class MagazineTag {

    private final int magazineRecord;
    private final String tagName;

    public MagazineTag(int magazineRecord, String tagName) {
        this.magazineRecord = magazineRecord;
        this.tagName = tagName;
    }

    public int getMagazineRecord() {return magazineRecord;}

    public String getTagName() {return tagName;}
}
