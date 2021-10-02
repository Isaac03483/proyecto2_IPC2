package com.ameri.enums;

public enum SubscriptionStatus {

    VIGENTE("vigente"),
    CANCELADA("cancelada");

    private final String status;

    SubscriptionStatus(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static SubscriptionStatus value(String string){

        if(string.equalsIgnoreCase(VIGENTE.getStatus())){
            return VIGENTE;

        } else if(string.equalsIgnoreCase(CANCELADA.getStatus())){
            return  CANCELADA;
        }
        return null;
    }
}
