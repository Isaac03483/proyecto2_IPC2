package com.ameri.operation.provisional;

import com.ameri.dao.magazine.DAOMagazineImpl;
import com.ameri.dao.user.editor.DAOCommentImpl;
import com.ameri.dao.user.editor.DAOEditorAccountImpl;
import com.ameri.dao.user.editor.DAOSubscriptionImpl;
import com.ameri.objects.beans.adminBeans.AdminBeans;
import com.ameri.objects.beans.adminBeans.GananciaBeans;
import com.ameri.objects.beans.adminBeans.OtherMagazineBeans;
import com.ameri.objects.classes.magazine.Magazine;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GetAdminBeansList {

    public static List<AdminBeans> getMagazineTopCommentsList(String startDate, String endDate) throws SQLException {
        List<AdminBeans> adminBeans;
        if(startDate.equals("") || endDate.equals("")){
            adminBeans = new DAOCommentImpl().listTopComments();
            for(AdminBeans beans: adminBeans){
                beans.setCommentList(new DAOCommentImpl().listMagazineComments(beans.getMagazineRecord()));
            }
        } else{
            adminBeans = new DAOCommentImpl().listTopCommentsBetween(LocalDate.parse(startDate), LocalDate.parse(endDate));
            for(AdminBeans beans: adminBeans){
                beans.setCommentList(new DAOCommentImpl().listMagazineCommentsBetween(beans.getMagazineRecord(),LocalDate.parse(startDate), LocalDate.parse(endDate) ));
            }
        }

        return adminBeans;
    }

    public static List<AdminBeans> getMagazineTopPopularList(String startDate, String endDate) throws SQLException{
        List<AdminBeans> adminBeans;
        if(startDate.equals("") || endDate.equals("")){
            adminBeans = new DAOSubscriptionImpl().listTopSubscriptions();
            for(AdminBeans beans: adminBeans){
                beans.setSubscriptionList(new DAOSubscriptionImpl().getListSubscriptions(beans.getMagazineRecord()));

            }
        } else{
            adminBeans = new DAOSubscriptionImpl().listTopSubscriptionsBetween(LocalDate.parse(startDate), LocalDate.parse(endDate));
            for(AdminBeans beans: adminBeans){
                beans.setSubscriptionList(new DAOSubscriptionImpl().getListSubscriptionsBetween(beans.getMagazineRecord(),LocalDate.parse(startDate), LocalDate.parse(endDate)));;
            }
        }

        return adminBeans;
    }

    public static List<GananciaBeans> getGananciaBeansList(String startDate, String endDate) throws SQLException{
        List<Magazine> magazineList = new DAOMagazineImpl().listAllMagazines();
        List<OtherMagazineBeans> otherMagazineBeans = new ArrayList<>();
        if(startDate.equals("") || endDate.equals("")){
            for(int i = 0; i < magazineList.size(); i++){
                otherMagazineBeans.add(new OtherMagazineBeans(magazineList.get(i).getMagazineRecord(), magazineList.get(i).getDayCost()));
                otherMagazineBeans.get(i).setSubscriptionList(new DAOEditorAccountImpl().listWhereMagazineRecord(magazineList.get(i).getMagazineRecord()));
                otherMagazineBeans.get(i).setGananciaTotal(LocalDate.now(),magazineList.get(i).getAcceptDate().toLocalDate());
            }
        } else{
            for(int i = 0; i < magazineList.size(); i++){
                otherMagazineBeans.add(new OtherMagazineBeans(magazineList.get(i).getMagazineRecord(), magazineList.get(i).getDayCost()));
                otherMagazineBeans.get(i).setSubscriptionList(new DAOEditorAccountImpl().listWhereMagazineRecordBetween(magazineList.get(i).getMagazineRecord(), LocalDate.parse(startDate),LocalDate.parse(endDate)));
                otherMagazineBeans.get(i).setGananciaTotal(LocalDate.parse(startDate),LocalDate.parse(endDate));
            }
        }

        List<GananciaBeans> gananciaBeans = new ArrayList<>();
        gananciaBeans.add(new GananciaBeans(otherMagazineBeans));
        gananciaBeans.get(0).updateTotal();

        return gananciaBeans;
    }
}
