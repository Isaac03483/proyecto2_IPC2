package com.ameri.objects.enums.user.editor;

public enum SubscriptionStatus {

    VIGENTE("VIGENTE"),
    CANCELADO("CANCELADO");

    private final String status;

    SubscriptionStatus(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static SubscriptionStatus value(String string){

        if(string.equalsIgnoreCase(VIGENTE.getStatus())){
            return VIGENTE;

        } else if(string.equalsIgnoreCase(CANCELADO.getStatus())){
            return  CANCELADO;
        }
        return null;
    }
}
