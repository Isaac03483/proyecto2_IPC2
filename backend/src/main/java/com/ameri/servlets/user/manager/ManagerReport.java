package com.ameri.servlets.user.manager;

import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.operation.services.EditorReportService;
import com.ameri.operation.services.ManagerReportService;
import net.sf.jasperreports.engine.JRException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/reports/manager-reports")
public class ManagerReport extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ManagerReportService reportService = null;

        resp.setContentType("application/pdf");
        resp.setHeader("Content-disposition", "attachment; filename=report.pdf");

        String report = req.getParameter("report");
        String reportType = req.getParameter("reportType");
        String startDateStr = req.getParameter("start");
        String endDateStr = req.getParameter("end");

        if(startDateStr.equalsIgnoreCase("null")|| endDateStr.equalsIgnoreCase("null")){
            startDateStr ="";
            endDateStr = "";
        }
        try {

                switch (reportType){
                    case "comments":
                        reportService = new ManagerReportService();
                        reportService.printMagazineTopComments(resp.getOutputStream(), startDateStr, endDateStr);
                             break;
                    case "subscriptions":
                        reportService = new ManagerReportService();
                        reportService.printMagazineTopSubscriptions(resp.getOutputStream(), startDateStr, endDateStr);
                        break;
                    case "gains":
                        reportService = new ManagerReportService();
                        reportService.printMagazineGainsReport(resp.getOutputStream(), startDateStr, endDateStr);
                        break;
                    case "likes":
                    default:
                        break;
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
