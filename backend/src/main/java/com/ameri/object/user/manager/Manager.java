package com.ameri.object.user.manager;

import com.ameri.object.enums.ManagerStatus;

public class Manager {

    private String managerName;
    private String managerPassword;
    private ManagerStatus managerStatus;

    public Manager(String managerName, String managerPassword) {
        this.managerName = managerName;
        this.managerPassword = managerPassword;
        managerStatus = ManagerStatus.VIGENTE;
    }

    public String getManagerName() {return managerName;}

    public String getManagerPassword() {return managerPassword;}

    public ManagerStatus getManagerStatus() {return managerStatus;}

    public void setManagerName(String managerName) {this.managerName = managerName;}

    public void setManagerPassword(String managerPassword) {this.managerPassword = managerPassword;}

    public void setManagerStatus(ManagerStatus managerStatus) {this.managerStatus = managerStatus;}
}
