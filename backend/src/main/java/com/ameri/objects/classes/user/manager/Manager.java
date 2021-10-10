package com.ameri.objects.classes.user.manager;

import com.ameri.objects.enums.user.manager.ManagerStatus;

public class Manager {

    private final String managerName;
    private final ManagerStatus managerStatus;
    private final String oldManagerName;

    public Manager(String managerName, ManagerStatus managerStatus) {
        this.managerName = managerName;
        oldManagerName="";
        this.managerStatus = managerStatus;
    }

    public Manager(String managerName, String oldManagerName){
        this.managerName=managerName;
        this.oldManagerName=oldManagerName;
        this.managerStatus= null;
    }

    public String getManagerName() {return managerName;}

    public ManagerStatus getManagerStatus() {return managerStatus;}

    public String getOldManagerName(){return this.oldManagerName;}

}
