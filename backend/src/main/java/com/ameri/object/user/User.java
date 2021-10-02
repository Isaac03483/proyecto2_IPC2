package com.ameri.object.user;

import com.ameri.enums.UserType;

public final class User {

    private final String userName;
    private final String userPassword;
    private final UserType userType;

    public User(String userName, String userPassword, UserType userType) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    public String getUserName() {return userName;}

    public String getUserPassword() {return this.userPassword;}

    public UserType getUserType(){return this.userType;}


}
