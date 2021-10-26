package com.ameri.servlets.user.editor.editorReport;

import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.operation.services.EditorReportService;
import net.sf.jasperreports.engine.JRException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/reports/editor-reports")
public class EditorReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditorReportService reportService = null;

        resp.setContentType("application/pdf");
        resp.setHeader("Content-disposition", "attachment; filename=report.pdf");

        String report = req.getParameter("report");
        String reportType = req.getParameter("reportType");
        String editorName = req.getParameter("editorName");
        String startDateStr = req.getParameter("start");
        String endDateStr = req.getParameter("end");

        System.out.println(startDateStr);
        if(startDateStr.equalsIgnoreCase("null")|| endDateStr.equalsIgnoreCase("null")){
            startDateStr ="";
            endDateStr = "";
        }
        System.out.println(startDateStr);
        try {
            if (report == null || report.equals("") || report.equals("1")) {

                switch (reportType){
                    case "comments":
                        reportService = new EditorReportService();
                        reportService.printAllMagazineComments(resp.getOutputStream(), new Profile(editorName), startDateStr, endDateStr);
                        break;
                    case "subscriptions":
                        reportService = new EditorReportService();
                        reportService.printAllMagazineSubscriptions(resp.getOutputStream(),new Profile(editorName), startDateStr, endDateStr);
                        break;
                    case "gains":
                        reportService = new EditorReportService();
                        reportService.printAllMagazineGains(resp.getOutputStream(),new Profile(editorName), startDateStr, endDateStr);
                        break;
                    default:

                        break;
                }

            } else if (report.equals("2")) {

                switch (reportType){
                    case "comments":
                        reportService = new EditorReportService();
                        reportService.printMagazineCommentsWithFilter(resp.getOutputStream(),new Profile(editorName), startDateStr, endDateStr);
                        break;
                    case "subscriptions":

                        reportService = new EditorReportService();
                        reportService.printMagazineSubscriptionsWithFilter(resp.getOutputStream(),new Profile(editorName), startDateStr, endDateStr);
                        break;
                    case "gains":
                        reportService = new EditorReportService();
                        reportService.printMagazineGainsWithFilter(resp.getOutputStream(),new Profile(editorName), startDateStr, endDateStr);
                        break;
                    case "likes":
                    default:

                        reportService = new EditorReportService();
                        reportService.printMagazineSubscriptionLikesWithFilter(resp.getOutputStream(),new Profile(editorName), startDateStr, endDateStr);
                        break;
                }
            }

        } catch (IOException | JRException | SQLException e) {
            // manejar la excepcion
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
