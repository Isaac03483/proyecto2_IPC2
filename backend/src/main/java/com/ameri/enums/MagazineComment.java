package com.ameri.enums;

public enum MagazineComment {

    SI("si"),
    NO("no");

    private final String status;

    MagazineComment(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static MagazineComment value(String string){
        if(string.equalsIgnoreCase(SI.getStatus())){
            return SI;
        } else if(string.equalsIgnoreCase(NO.getStatus())){
            return NO;
        }
        return null;
    }
}
