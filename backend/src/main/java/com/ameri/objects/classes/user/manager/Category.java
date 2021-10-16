package com.ameri.objects.classes.user.manager;

public class Category {

    private final String categoryName;
    private final String categoryOldName;

    public Category(String categoryName, String categoryOldName){

        this.categoryName = categoryName;
        this.categoryOldName = categoryOldName;
    }

    public Category(String categoryName){
        this.categoryName = categoryName;
        this.categoryOldName=null;
    }
    public String getCategoryName(){return this.categoryName;}

    public String getCategoryOldName() {return this.categoryOldName;}
}
