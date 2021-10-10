package com.ameri.objects.classes.user;

import com.ameri.objects.enums.user.UserType;

public class User {

    private final String userName;
    private final String userPassword;
    private final String oldUserName;
    private final UserType userType;

    public User(String userName, String userPassword, UserType userType) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.oldUserName=null;
        this.userType =userType;
    }

    public User(String userName, String oldUserName){
        this.userName = userName;
        this.oldUserName = oldUserName;
        this.userPassword = null;
        this.userType = null;
    }

    public String getUserName() {return userName;}

    public String getUserPassword() {return this.userPassword;}

    public UserType getUserType(){return this.userType;}

    public String getOldUserName(){return this.oldUserName;}

}
