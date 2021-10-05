package com.ameri.objects.enums.magazine;

public enum MagazineLike {

    SI("SI"),
    NO("NO");

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
