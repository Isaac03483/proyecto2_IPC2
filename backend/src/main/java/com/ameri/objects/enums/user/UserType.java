package com.ameri.objects.enums.user;

public enum UserType {

    EDITOR("EDITOR"),
    ADMIN("ADMIN");

    private final String type;

    UserType(String type){
        this.type = type;
    }

    public String getType(){return this.type;}


    public static UserType value(String string){
        if(string.equalsIgnoreCase(EDITOR.getType())){
            return EDITOR;
        } else if(string.equalsIgnoreCase(ADMIN.getType())){
            return ADMIN;
        }

        return null;
    }
}
