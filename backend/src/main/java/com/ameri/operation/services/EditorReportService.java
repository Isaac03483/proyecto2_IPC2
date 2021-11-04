package com.ameri.operation.services;

import com.ameri.objects.beans.editorBeans.EditorBeans;
import com.ameri.objects.beans.editorBeans.MagazineBeans;
import com.ameri.objects.classes.user.editor.Comment;
import com.ameri.objects.classes.user.editor.EditorAccount;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.editor.Subscription;
import com.ameri.operation.provisional.GetMagazineBeansList;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EditorReportService {


    public EditorReportService() {
    }


    public void printAllMagazineComments(OutputStream targetStream, EditorBeans editorBeans) throws JRException, SQLException, IOException {

        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/editor/magazineCommentsReport1.jasper");

        List<EditorBeans> beansList = new ArrayList<>();
        beansList.add(editorBeans);
        JRDataSource source = new JRBeanCollectionDataSource(beansList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printMagazineCommentsWithFilter(OutputStream targetStream, EditorBeans editorBeans) throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/editor/magazineCommentsReport2.jasper");

        List<EditorBeans> beansList = new ArrayList<>();
        beansList.add(editorBeans);
        JRDataSource source = new JRBeanCollectionDataSource(beansList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }


    public void printMagazineSubscriptionsWithFilter(OutputStream targetStream, EditorBeans editorBeans) throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/editor/magazineSubscriptionsReport2.jasper");

        List<EditorBeans> beansList = new ArrayList<>();
        beansList.add(editorBeans);
        JRDataSource source = new JRBeanCollectionDataSource(beansList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printAllMagazineSubscriptions(OutputStream targetStream,EditorBeans editorBeans) throws JRException, SQLException, IOException {

        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/editor/magazineSubscriptionsReport1.jasper");

        List<EditorBeans> beansList = new ArrayList<>();
        beansList.add(editorBeans);
        JRDataSource source = new JRBeanCollectionDataSource(beansList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printMagazineGainsWithFilter(OutputStream targetStream, EditorBeans editorBeans) throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/editor/magazineGainsReport2.jasper");

        List<EditorBeans> beansList = new ArrayList<>();
        beansList.add(editorBeans);
        JRDataSource source = new JRBeanCollectionDataSource(beansList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printAllMagazineGains(OutputStream targetStream, EditorBeans editorBeans) throws JRException, SQLException, IOException {

        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/editor/magazineGainsReport1.jasper");

        List<EditorBeans> beansList = new ArrayList<>();
        beansList.add(editorBeans);
        JRDataSource source = new JRBeanCollectionDataSource(beansList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }

    public void printMagazineSubscriptionLikesWithFilter(OutputStream targetStream, EditorBeans editorBeans) throws JRException, SQLException, IOException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/ameri/reports/editor/magazineTopLikesReport.jasper");

        List<EditorBeans> beansList = new ArrayList<>();
        beansList.add(editorBeans);
        JRDataSource source = new JRBeanCollectionDataSource(beansList);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);

        targetStream.flush();
        targetStream.close();
    }
}