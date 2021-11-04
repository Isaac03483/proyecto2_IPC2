package com.ameri.operation.services;

import com.ameri.objects.beans.adminBeans.AdminBeans;
import com.ameri.objects.beans.adminBeans.GananciaBeans;
import com.ameri.objects.beans.adminBeans.ProvisionalBean;
import com.ameri.operation.provisional.GetAdminBeansList;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerReportService {

    public void printMagazineTopComments(OutputStream targetStream, ProvisionalBean provisionalBean) throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/admin/adminTopCommentsReport.jasper");

        List<ProvisionalBean> provisionalBeans = new ArrayList<>();
        provisionalBeans.add(provisionalBean);

        JRDataSource source = new JRBeanCollectionDataSource(provisionalBeans);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printMagazineTopSubscriptions(OutputStream targetStream, ProvisionalBean provisionalBean) throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/admin/adminTopPopularReport.jasper");

        List<ProvisionalBean> provisionalBeans = new ArrayList<>();
        provisionalBeans.add(provisionalBean);

        JRDataSource source = new JRBeanCollectionDataSource(provisionalBeans);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printMagazineGainsReport(OutputStream targetStream, ProvisionalBean provisionalBean)throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/admin/adminGainsReport.jasper");

        List<ProvisionalBean> provisionalBeans = new ArrayList<>();
        provisionalBeans.add(provisionalBean);

        JRDataSource source = new JRBeanCollectionDataSource(provisionalBeans);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }
}
