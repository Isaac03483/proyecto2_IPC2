package com.ameri.operation.services;

import com.ameri.dao.magazine.DAOMagazineImpl;
import com.ameri.dao.user.editor.DAOCommentImpl;
import com.ameri.dao.user.editor.DAOEditorAccountImpl;
import com.ameri.dao.user.editor.DAOSubscriptionImpl;
import com.ameri.objects.Beans.AdminBeans;
import com.ameri.objects.Beans.GananciaBeans;
import com.ameri.objects.Beans.OtherMagazineBeans;
import com.ameri.objects.classes.magazine.Magazine;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerReportService {

    public void printMagazineTopComments(OutputStream targetStream, String startDate, String endDate) throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/adminTopCommentsReport2.jasper");

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


        JRDataSource source = new JRBeanCollectionDataSource(adminBeans);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printMagazineTopSubscriptions(OutputStream targetStream, String startDate, String endDate) throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/adminTopSubscriptionsReport2.jasper");

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


        JRDataSource source = new JRBeanCollectionDataSource(adminBeans);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printMagazineGainsReport(OutputStream targetStream, String startDate, String endDate)throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/adminGananciaRepor2.jasper");

        List<Magazine> magazineList = new DAOMagazineImpl().listAllMagazines();
        List<OtherMagazineBeans> otherMagazineBeans = new ArrayList<>();
        if(startDate.equals("") || endDate.equals("")){
            for(int i = 0; i < magazineList.size(); i++){
                otherMagazineBeans.add(new OtherMagazineBeans(magazineList.get(i).getMagazineRecord(), magazineList.get(i).getDayCost()));
                otherMagazineBeans.get(i).setSubscriptionList(new DAOEditorAccountImpl().listWhereMagazineRecord(magazineList.get(i).getMagazineRecord()));
                otherMagazineBeans.get(i).setGananciaTotal(magazineList.get(i).getAcceptDate().toLocalDate(),LocalDate.now());
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
        JRDataSource source = new JRBeanCollectionDataSource(gananciaBeans);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }
}
