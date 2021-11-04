package com.ameri.servlets.user.editor.editorReport;

import com.ameri.converter.beans.EditorBeansConverter;
import com.ameri.converter.beans.MagazineBeansConverter;
import com.ameri.converter.user.editor.CommentConverter;
import com.ameri.converter.user.editor.EditorAccountConverter;
import com.ameri.converter.user.editor.SubscriptionConverter;
import com.ameri.objects.beans.editorBeans.EditorBeans;
import com.ameri.objects.beans.editorBeans.MagazineBeans;
import com.ameri.objects.classes.user.editor.Comment;
import com.ameri.objects.classes.user.editor.EditorAccount;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.editor.Subscription;
import com.ameri.operation.provisional.GetMagazineBeansList;
import com.ameri.operation.provisional.Reader;
import com.ameri.operation.services.EditorReportService;
import net.sf.jasperreports.engine.JRException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/reports/editor-reports")
public class EditorExportReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String report = req.getParameter("report");
        String reportType = req.getParameter("reportType");
        String editorName = req.getParameter("editorName");
        String startDateStr = req.getParameter("start");
        String endDateStr = req.getParameter("end");

        if(startDateStr.equalsIgnoreCase("null")|| endDateStr.equalsIgnoreCase("null")){
            startDateStr ="";
            endDateStr = "";
        }

        try{
            EditorBeans beansList = new EditorBeans();
            if (report == null || report.equals("") || report.equals("1")) {

                switch (reportType){
                    case "comments":

                        beansList = GetMagazineBeansList.getAllMagazineComment(new Profile(editorName), startDateStr, endDateStr);
                        break;
                    case "subscriptions":

                        beansList = GetMagazineBeansList.getAllMagazineSubscription(new Profile(editorName), startDateStr, endDateStr);
                        break;
                    case "gains":

                        beansList = GetMagazineBeansList.getAllMagazineGains(new Profile(editorName), startDateStr, endDateStr);
                        break;
                    default:

                        break;
                }

            } else if (report.equals("2")) {

                switch (reportType){
                    case "comments":

                        beansList = GetMagazineBeansList.getMagazineCommentBeansListWithFilter(new Profile(editorName), startDateStr, endDateStr);
                        break;
                    case "subscriptions":

                        beansList = GetMagazineBeansList.getMagazineSubscriptionsBeansListWithFilter(new Profile(editorName), startDateStr, endDateStr);
                        break;
                    case "gains":

                        beansList = GetMagazineBeansList.getMagazineGainsBeansListWithFilter(new Profile(editorName), startDateStr, endDateStr);
                        break;
                    case "likes":
                    default:

                        beansList = GetMagazineBeansList.getTopPopularMagazines(new Profile(editorName), startDateStr, endDateStr);
                        break;
                }

            }

            System.out.println(beansList);
            resp.getWriter().write(new EditorBeansConverter(EditorBeans.class).toJson(beansList));
        } catch (SQLException ignored){

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditorReportService reportService = null;

        resp.setContentType("application/pdf");
        resp.setHeader("Content-disposition", "attachment; filename=report.pdf");

        String report = req.getParameter("report");
        String reportType = req.getParameter("reportType");

        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        EditorBeansConverter converter = new EditorBeansConverter(EditorBeans.class);
        EditorBeans editorBeans = converter.fromJson(body);
        try {
            if (report == null || report.equals("") || report.equals("1")) {


                switch (reportType){
                    case "comments":
                        reportService = new EditorReportService();
                        reportService.printAllMagazineComments(resp.getOutputStream(), editorBeans);
                        break;
                    case "subscriptions":
                        reportService = new EditorReportService();
                        reportService.printAllMagazineSubscriptions(resp.getOutputStream(),editorBeans);
                        break;
                    case "gains":
                        reportService = new EditorReportService();
                        reportService.printAllMagazineGains(resp.getOutputStream(),editorBeans);
                        break;
                    default:

                        break;
                }

            } else if (report.equals("2")) {

                switch (reportType){
                    case "comments":
                        reportService = new EditorReportService();
                        reportService.printMagazineCommentsWithFilter(resp.getOutputStream(),editorBeans);
                        break;
                    case "subscriptions":

                        reportService = new EditorReportService();
                        reportService.printMagazineSubscriptionsWithFilter(resp.getOutputStream(),editorBeans);
                        break;
                    case "gains":
                        reportService = new EditorReportService();
                        reportService.printMagazineGainsWithFilter(resp.getOutputStream(),editorBeans);
                        break;
                    case "likes":
                    default:

                        reportService = new EditorReportService();
                        reportService.printMagazineSubscriptionLikesWithFilter(resp.getOutputStream(),editorBeans);
                        break;
                }
            }

        } catch (IOException | JRException | SQLException e) {
            // manejar la excepcion
            e.printStackTrace();
        }
    }
}
