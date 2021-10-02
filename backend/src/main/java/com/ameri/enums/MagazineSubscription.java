package com.ameri.enums;

public enum MagazineSubscription {

    SI("si"),
    NO("no");

    private final String status;

    MagazineSubscription(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static MagazineSubscription value(String string){
        if(string.equalsIgnoreCase(SI.getStatus())){
            return SI;
        } else if(string.equalsIgnoreCase(NO.getStatus())){
            return NO;
        }
        return null;
    }
}
