package com.ameri.enums;

public enum AdsStatus {
    ACTIVO("activo"),
    INACTIVO("inactivo");

    private final String status;

    AdsStatus(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static AdsStatus value(String string){
        if(string.equalsIgnoreCase(ACTIVO.getStatus())){
            return ACTIVO;
        } else if(string.equalsIgnoreCase(INACTIVO.getStatus())){
            return INACTIVO;
        }

        return null;
    }
}
