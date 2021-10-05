package com.ameri.objects.enums.user.editor;

public enum PaymentEnum {

    MENSUAL("MENSUAL"),
    ANUAL("ANUAL");

    private final String interval;

    PaymentEnum(String interval){
        this.interval = interval;
    }

    public String getInterval(){return this.interval;}

    public static PaymentEnum value(String string){
        if(string.equalsIgnoreCase(MENSUAL.getInterval())){
            return MENSUAL;
        } else if(string.equalsIgnoreCase(ANUAL.getInterval())){
            return ANUAL;
        }

        return null;
    }
}
