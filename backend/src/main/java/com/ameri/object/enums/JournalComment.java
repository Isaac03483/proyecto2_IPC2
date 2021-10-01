package com.ameri.object.enums;

public enum JournalComment {

    SI("si"),
    NO("no");

    private final String status;

    JournalComment(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static JournalComment value(String string){
        if(string.equalsIgnoreCase(SI.getStatus())){
            return SI;
        } else if(string.equalsIgnoreCase(NO.getStatus())){
            return NO;
        }
        return null;
    }
}
