package com.ameri.objects.classes.user.manager;

public class Tag {

    private final String tagName;
    private final String oldTagName;

    public Tag(String tagName) {
        this.tagName = tagName;
        this.oldTagName = "";
    }

    public Tag(String tagName, String oldTagName){
        this.tagName =tagName;
        this.oldTagName = oldTagName;
    }
    public String getTagName() {return this.tagName;}

    public String getOldTagName(){return this.oldTagName;}
}
