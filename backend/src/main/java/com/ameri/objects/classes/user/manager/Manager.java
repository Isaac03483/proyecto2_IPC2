package com.ameri.objects.classes.user.manager;

import com.ameri.objects.enums.user.manager.ManagerStatus;

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
