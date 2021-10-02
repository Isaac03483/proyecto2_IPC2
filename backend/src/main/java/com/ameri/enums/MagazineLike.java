package com.ameri.enums;

public enum MagazineLike {

    SI("si"),
    NO("no");

    private final String status;

    MagazineLike(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static MagazineLike value(String string){
        if(string.equalsIgnoreCase(SI.getStatus())){
            return SI;
        } else if(string.equalsIgnoreCase(NO.getStatus())){
            return NO;
        }
        return null;
    }

}
