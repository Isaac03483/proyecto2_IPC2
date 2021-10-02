package com.ameri.enums;

public enum ManagerStatus {

    VIGENTE("vigente"),
    CANCELADO("cancelado");

    private final String status;

    ManagerStatus(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static ManagerStatus value(String string){
        if(string.equalsIgnoreCase(VIGENTE.getStatus())){
            return VIGENTE;
        } else if(string.equalsIgnoreCase(CANCELADO.getStatus())){
            return CANCELADO;
        }

        return null;
    }
}
