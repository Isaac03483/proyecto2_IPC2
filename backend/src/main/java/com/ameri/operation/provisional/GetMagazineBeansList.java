package com.ameri.operation.provisional;

import com.ameri.dao.magazine.DAOMagazineImpl;
import com.ameri.dao.user.editor.DAOCommentImpl;
import com.ameri.dao.user.editor.DAOEditorAccountImpl;
import com.ameri.dao.user.editor.DAOSubscriptionImpl;
import com.ameri.objects.beans.editorBeans.EditorBeans;
import com.ameri.objects.beans.editorBeans.MagazineBeans;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.user.editor.Comment;
import com.ameri.objects.classes.user.editor.EditorAccount;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.editor.Subscription;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GetMagazineBeansList {

    private static List<MagazineBeans> getMagazineList(Profile profile) throws SQLException {
        List<Magazine> magazineList = new DAOMagazineImpl().listEditorMagazines(profile);

        List<MagazineBeans> beansList = new ArrayList<>();

        for(Magazine magazine: magazineList){
            beansList.add(new MagazineBeans(magazine.getMagazineRecord(), magazine.getMagazineName()));
        }

        return beansList;
    }

    public static EditorBeans getAllMagazineComment(Profile profile, String startDate, String endDate) throws SQLException{
        List<MagazineBeans> beansList = getMagazineList(profile);

        List<Comment> commentList = new ArrayList<>();
        if(startDate.equals("") || endDate.equals("")){

            for (MagazineBeans magazineBeans : beansList) {
                commentList.addAll(new DAOCommentImpl().listMagazineComments(magazineBeans.getMagazineRecord()));
            }
        } else{

            for(MagazineBeans beans: beansList){
                commentList.addAll(new DAOCommentImpl().listMagazineCommentsBetween(beans.getMagazineRecord(), LocalDate.parse(startDate), LocalDate.parse(endDate)));

            }
        }

        EditorBeans editorBeans = new EditorBeans();
        editorBeans.setCommentList(commentList);
        editorBeans.setTotal(commentList.size());

        return  editorBeans;
    }

    public static EditorBeans getMagazineCommentBeansListWithFilter(Profile profile, String startDate, String endDate) throws SQLException {
        List<MagazineBeans> beansList = getMagazineList(profile);


        EditorBeans editorBeans = new EditorBeans();
        if(startDate.equals("") || endDate.equals("")){
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setCommentList(new DAOCommentImpl().listMagazineComments(magazineBeans.getMagazineRecord()));
                magazineBeans.setTotalCount(magazineBeans.getCommentList().size());
                editorBeans.setTotal(magazineBeans.getTotalCount());

            }
        } else{
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setCommentList(new DAOCommentImpl().listMagazineCommentsBetween(magazineBeans.getMagazineRecord(), LocalDate.parse(startDate), LocalDate.parse(endDate)));
                magazineBeans.setTotalCount(magazineBeans.getCommentList().size());
                editorBeans.setTotal(magazineBeans.getTotalCount());
            }
        }

        editorBeans.setMagazineBeansList(beansList);
        return editorBeans;
    }

    public static EditorBeans getAllMagazineSubscription(Profile profile, String startDate, String endDate) throws SQLException{
        List<MagazineBeans> beansList = getMagazineList(profile);

        List<Subscription> subscriptionList = new ArrayList<>();
        if(startDate.equals("") || endDate.equals("")){
            for(MagazineBeans beans: beansList){
                subscriptionList.addAll(new DAOSubscriptionImpl().getListSubscriptions(beans.getMagazineRecord()));
            }
        } else{
            for(MagazineBeans beans: beansList){
                subscriptionList.addAll(new DAOSubscriptionImpl().getListSubscriptionsBetween(beans.getMagazineRecord(), LocalDate.parse(startDate), LocalDate.parse(endDate)));
            }
        }

        EditorBeans editorBeans = new EditorBeans();
        editorBeans.setSubscriptionList(subscriptionList);
        for(Subscription subscription: subscriptionList){
            editorBeans.setTotal(subscription.getTotalPay().doubleValue());
        }
        return  editorBeans;
    }

    public static EditorBeans getMagazineSubscriptionsBeansListWithFilter(Profile profile, String startDate, String endDate) throws SQLException {
        List<MagazineBeans> beansList = getMagazineList(profile);


        EditorBeans editorBeans = new EditorBeans();
        if(startDate.equals("") || endDate.equals("")){
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setSubscriptionList(new DAOSubscriptionImpl().getListSubscriptions(magazineBeans.getMagazineRecord()));
                for(Subscription subscription: magazineBeans.getSubscriptionList()){
                    magazineBeans.setTotalCount(subscription.getTotalPay().doubleValue());
                    editorBeans.setTotal(subscription.getTotalPay().doubleValue());
                }
            }
        } else{
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setSubscriptionList(new DAOSubscriptionImpl().getListSubscriptionsBetween(magazineBeans.getMagazineRecord(), LocalDate.parse(startDate), LocalDate.parse(endDate)));
                for(Subscription subscription: magazineBeans.getSubscriptionList()){
                    magazineBeans.setTotalCount(subscription.getTotalPay().doubleValue());
                    editorBeans.setTotal(subscription.getTotalPay().doubleValue());
                }
            }
        }

        editorBeans.setMagazineBeansList(beansList);
        return editorBeans;
    }

    public static EditorBeans getAllMagazineGains(Profile profile, String startDate, String endDate) throws SQLException{

        List<MagazineBeans> beansList = getMagazineList(profile);

        List<EditorAccount> editorAccountList = new ArrayList<>();

        if(startDate.equals("") || endDate.equals("")){

            for(MagazineBeans beans: beansList){
                editorAccountList.addAll(new DAOEditorAccountImpl().listWhereMagazineRecord(beans.getMagazineRecord()));
            }
        } else{
            for(MagazineBeans beans: beansList){
                editorAccountList.addAll(new DAOEditorAccountImpl().listWhereMagazineRecordBetween(beans.getMagazineRecord(), LocalDate.parse(startDate), LocalDate.parse(endDate)));
            }
        }

        EditorBeans editorBeans = new EditorBeans();
        editorBeans.setEditorAccountList(editorAccountList);
        for(EditorAccount editorAccount: editorAccountList){
            editorBeans.setTotal(editorAccount.getGanancia().doubleValue());
        }

        return  editorBeans;
    }

    public static EditorBeans getMagazineGainsBeansListWithFilter(Profile profile, String startDate, String endDate) throws SQLException {

        List<MagazineBeans> beansList = getMagazineList(profile);

        EditorBeans editorBeans = new EditorBeans();
        if(startDate.equals("") || endDate.equals("")){
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setEditorAccountList(new DAOEditorAccountImpl().listWhereMagazineRecord(magazineBeans.getMagazineRecord()));
                for(EditorAccount editorAccount: magazineBeans.getEditorAccountList()){
                    magazineBeans.setTotalCount(editorAccount.getGanancia().doubleValue());
                    editorBeans.setTotal(editorAccount.getGanancia().doubleValue());
                }
            }
        } else{
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setEditorAccountList(new DAOEditorAccountImpl().listWhereMagazineRecordBetween(magazineBeans.getMagazineRecord(), LocalDate.parse(startDate), LocalDate.parse(endDate)));
                for(EditorAccount editorAccount: magazineBeans.getEditorAccountList()){
                    magazineBeans.setTotalCount(editorAccount.getGanancia().doubleValue());
                    editorBeans.setTotal(editorAccount.getGanancia().doubleValue());
                }
            }
        }

        editorBeans.setMagazineBeansList(beansList);
        return editorBeans;
    }

    public static EditorBeans getTopPopularMagazines(Profile profile, String startDate, String endDate) throws SQLException {

        List<MagazineBeans> beansList = getMagazineList(profile);

        EditorBeans editorBeans = new EditorBeans();
        if(startDate.equals("") || endDate.equals("")){
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setMagazineLikes(new DAOSubscriptionImpl().getSubscriptionNumberLikes(magazineBeans.getMagazineRecord()));
                editorBeans.setTotal(magazineBeans.getMagazineLikes());
            }

            beansList.sort(new Comparator<MagazineBeans>() {
                @Override
                public int compare(MagazineBeans magazineBeans, MagazineBeans t1) {
                    return Integer.compare(t1.getMagazineLikes(), magazineBeans.getMagazineLikes());
                }

            });
            while(beansList.size()> 5){
                beansList.remove(beansList.size()-1);
            }

            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setSubscriptionList(new DAOSubscriptionImpl().listWhereSubscriptionLike(magazineBeans.getMagazineRecord()));
            }
        } else{
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setMagazineLikes(new DAOSubscriptionImpl().getSubscriptionNumberLikesBetween(magazineBeans.getMagazineRecord(), LocalDate.parse(startDate), LocalDate.parse(endDate)));
                editorBeans.setTotal(magazineBeans.getMagazineLikes());
            }

            beansList.sort((p1, p2) -> Integer.compare(p1.getMagazineLikes(), p2.getMagazineLikes()));

            while(beansList.size()> 5){
                beansList.remove(beansList.size()-1);
            }

            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setSubscriptionList(new DAOSubscriptionImpl().getListSubscriptionsLikesBetween(magazineBeans.getMagazineRecord(),LocalDate.parse(startDate), LocalDate.parse(endDate)));
            }
        }

        editorBeans.setMagazineBeansList(beansList);
        return editorBeans;
    }
}
