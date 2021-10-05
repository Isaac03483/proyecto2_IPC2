package com.ameri.objects.enums.user.editor;

public enum SubscriptionLike {

    SI("SI"),
    NO("NO");

    private final String status;

    SubscriptionLike(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static SubscriptionLike value(String string){
        if(string.equalsIgnoreCase(SI.getStatus())){
            return SI;
        } else if(string.equalsIgnoreCase(NO.getStatus())){
            return NO;
        }
        return null;
    }
}
