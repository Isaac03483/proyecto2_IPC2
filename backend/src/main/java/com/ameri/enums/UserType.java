package com.ameri.enums;

public enum UserType {

    USER("user"),
    ADMIN("admin");

    private final String type;

    UserType(String type){
        this.type = type;
    }

    public String getType(){return this.type;}

    public static UserType value(String string){
        if(string.equalsIgnoreCase(USER.getType())){
            return USER;
        } else if(string.equalsIgnoreCase(ADMIN.getType())){
            return ADMIN;
        }

        return null;
    }
}
