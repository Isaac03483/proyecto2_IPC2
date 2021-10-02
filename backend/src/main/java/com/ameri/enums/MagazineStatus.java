package com.ameri.enums;

public enum MagazineStatus {

    ACEPTADA("aceptada"),
    ENESPERA("en espera");

    private final String status;

    MagazineStatus(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static MagazineStatus value(String string){
        if(string.equalsIgnoreCase(ACEPTADA.getStatus())){
            return ACEPTADA;
        } else if(string.equalsIgnoreCase(ENESPERA.getStatus())){
            return ENESPERA;
        }
        return null;
    }
}
