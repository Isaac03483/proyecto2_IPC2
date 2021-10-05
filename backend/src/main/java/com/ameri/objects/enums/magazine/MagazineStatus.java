package com.ameri.objects.enums.magazine;

public enum MagazineStatus {

    ACEPTADA("ACEPTADA"),
    ENESPERA("EN ESPERA");

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
