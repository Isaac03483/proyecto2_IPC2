package com.ameri.objects.beans.adminBeans;

import java.util.List;

public class ProvisionalBean {

    List<AdminBeans> adminBeansList;
    List<GananciaBeans> gananciaBeans;

    public ProvisionalBean(){}

    public ProvisionalBean(List<AdminBeans> adminBeansList, List<GananciaBeans> gananciaBeans) {
        this.adminBeansList = adminBeansList;
        this.gananciaBeans = gananciaBeans;
    }

    public List<AdminBeans> getAdminBeansList() {
        return adminBeansList;
    }

    public void setAdminBeansList(List<AdminBeans> adminBeansList) {
        this.adminBeansList = adminBeansList;
    }

    public List<GananciaBeans> getGananciaBeans() {
        return gananciaBeans;
    }

    public void setGananciaBeans(List<GananciaBeans> gananciaBeans) {
        this.gananciaBeans = gananciaBeans;
    }
}
