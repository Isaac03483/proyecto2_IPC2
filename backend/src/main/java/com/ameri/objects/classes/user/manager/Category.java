package com.ameri.objects.classes.user.manager;

public class Category {

    private final String categoryName;
    private final int categoryRecord;

    public Category(String categoryName, int categoryRecord){

        this.categoryName = categoryName;
        this.categoryRecord = categoryRecord;
    }

    public String getCategoryName(){return this.categoryName;}

    public int getCategoryRecord() {return categoryRecord;}
}
