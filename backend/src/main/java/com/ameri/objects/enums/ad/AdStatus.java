package com.ameri.objects.enums.ad;

public enum AdStatus {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    VENCIDO("VENCIDO");

    private final String status;

    AdStatus(String status){
        this.status = status;
    }

    public String getStatus(){return this.status;}

    public static AdStatus value(String string){
        if(string.equalsIgnoreCase(ACTIVO.getStatus())){
            return ACTIVO;
        } else if(string.equalsIgnoreCase(INACTIVO.getStatus())){
            return INACTIVO;
        } else if(string.equalsIgnoreCase(VENCIDO.getStatus())){
            return VENCIDO;
        }

        return null;
    }
}
