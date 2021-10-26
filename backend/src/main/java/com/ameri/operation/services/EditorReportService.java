package com.ameri.operation.services;

import com.ameri.dao.magazine.DAOMagazineImpl;
import com.ameri.dao.user.editor.DAOCommentImpl;
import com.ameri.dao.user.editor.DAOEditorAccountImpl;
import com.ameri.dao.user.editor.DAOSubscriptionImpl;
import com.ameri.objects.Beans.MagazineBeans;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.user.editor.Comment;
import com.ameri.objects.classes.user.editor.EditorAccount;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.editor.Subscription;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EditorReportService {


    public EditorReportService() {
    }

    public void printAllMagazineComments(OutputStream targetStream, Profile profile, String startDate, String endDate) throws JRException, SQLException, IOException {

        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/magazineCommentsReport1.jasper");

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

        JRDataSource source = new JRBeanCollectionDataSource(commentList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printMagazineCommentsWithFilter(OutputStream targetStream, Profile profile, String startDate, String endDate) throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/magazineCommentsReport2.jasper");

        List<MagazineBeans> beansList = getMagazineList(profile);


        if(startDate.equals("") || endDate.equals("")){
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setCommentList(new DAOCommentImpl().listMagazineComments(magazineBeans.getMagazineRecord()));
            }
        } else{
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setCommentList(new DAOCommentImpl().listMagazineCommentsBetween(magazineBeans.getMagazineRecord(), LocalDate.parse(startDate), LocalDate.parse(endDate)));
            }
        }


        JRDataSource source = new JRBeanCollectionDataSource(beansList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    private List<MagazineBeans> getMagazineList(Profile profile) throws SQLException {
        List<Magazine> magazineList = new DAOMagazineImpl().listEditorMagazines(profile);

        List<MagazineBeans> beansList = new ArrayList<>();

        for(Magazine magazine: magazineList){
            beansList.add(new MagazineBeans(magazine.getMagazineRecord(), magazine.getMagazineName()));
        }

        return beansList;
    }

    public void printMagazineSubscriptionsWithFilter(OutputStream targetStream, Profile profile, String startDate, String endDate) throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/magazineSubscriptionsReport2.jasper");

        List<MagazineBeans> beansList = getMagazineList(profile);


        if(startDate.equals("") || endDate.equals("")){
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setSubscriptionList(new DAOSubscriptionImpl().getListSubscriptions(magazineBeans.getMagazineRecord()));
            }
        } else{
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setSubscriptionList(new DAOSubscriptionImpl().getListSubscriptionsBetween(magazineBeans.getMagazineRecord(), LocalDate.parse(startDate), LocalDate.parse(endDate)));
            }
        }

        JRDataSource source = new JRBeanCollectionDataSource(beansList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printAllMagazineSubscriptions(OutputStream targetStream, Profile profile, String startDate, String endDate) throws JRException, SQLException, IOException {

        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/magazineSubscriptionReport1.jasper");

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

        JRDataSource source = new JRBeanCollectionDataSource(subscriptionList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printMagazineGainsWithFilter(OutputStream targetStream, Profile profile, String startDate, String endDate) throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/magazineGainsReport2.jasper");

        List<MagazineBeans> beansList = getMagazineList(profile);

        if(startDate.equals("") || endDate.equals("")){
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setEditorAccountList(new DAOEditorAccountImpl().listWhereMagazineRecord(magazineBeans.getMagazineRecord()));
            }
        } else{
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setEditorAccountList(new DAOEditorAccountImpl().listWhereMagazineRecordBetween(magazineBeans.getMagazineRecord(), LocalDate.parse(startDate), LocalDate.parse(endDate)));
            }
        }

        JRDataSource source = new JRBeanCollectionDataSource(beansList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printAllMagazineGains(OutputStream targetStream, Profile profile, String startDate, String endDate) throws JRException, SQLException, IOException {

        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/magazineGainsReport1.jasper");

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

        JRDataSource source = new JRBeanCollectionDataSource(editorAccountList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printMagazineSubscriptionLikesWithFilter(OutputStream targetStream, Profile profile, String startDate, String endDate) throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/magazineLikesReport2.jasper");

        List<MagazineBeans> beansList = getMagazineList(profile);

        if(startDate.equals("") || endDate.equals("")){
            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setMagazineLikes(new DAOSubscriptionImpl().getSubscriptionNumberLikes(magazineBeans.getMagazineRecord()));
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
            }

            beansList.sort((p1, p2) -> Integer.compare(p1.getMagazineLikes(), p2.getMagazineLikes()));

            while(beansList.size()> 5){
                beansList.remove(beansList.size()-1);
            }

            for (MagazineBeans magazineBeans : beansList) {
                magazineBeans.setSubscriptionList(new DAOSubscriptionImpl().getListSubscriptionsLikesBetween(magazineBeans.getMagazineRecord(),LocalDate.parse(startDate), LocalDate.parse(endDate)));
            }
        }

        JRDataSource source = new JRBeanCollectionDataSource(beansList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }
}