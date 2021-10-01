package com.ameri.object.enums;

public enum JournalSuscription {

    SI("si"),
    NO("no");

    private final String status;

    JournalSuscription(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static JournalSuscription value(String string){
        if(string.equalsIgnoreCase(SI.getStatus())){
            return SI;
        } else if(string.equalsIgnoreCase(NO.getStatus())){
            return NO;
        }
        return null;
    }
}
