package com.ameri.object.user.manager;

import com.ameri.enums.ManagerStatus;

public class Manager {

    private final String managerName;
    private final ManagerStatus managerStatus;

    public Manager(String managerName) {
        this.managerName = managerName;
        managerStatus = ManagerStatus.VIGENTE;
    }

    public Manager(String managerName, ManagerStatus managerStatus) {
        this.managerName = managerName;
        this.managerStatus = managerStatus;
    }

    public String getManagerName() {return managerName;}

    public ManagerStatus getManagerStatus() {return managerStatus;}

}
