package com.ameri.object.enums;

public enum JournalStatus {

    ACEPTADA("aceptada"),
    ENESPERA("en espera");

    private final String status;

    JournalStatus(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static JournalStatus value(String string){
        if(string.equalsIgnoreCase(ACEPTADA.getStatus())){
            return ACEPTADA;
        } else if(string.equalsIgnoreCase(ENESPERA.getStatus())){
            return ENESPERA;
        }
        return null;
    }
}
