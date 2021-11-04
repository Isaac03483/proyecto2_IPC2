package com.ameri.servlets.user.manager;

import com.ameri.converter.beans.AdminBeansConverter;
import com.ameri.converter.beans.GananciaBeansConverter;
import com.ameri.converter.beans.ProvisionalBeanConverter;
import com.ameri.objects.beans.adminBeans.AdminBeans;
import com.ameri.objects.beans.adminBeans.GananciaBeans;
import com.ameri.objects.beans.adminBeans.ProvisionalBean;
import com.ameri.operation.provisional.GetAdminBeansList;
import com.ameri.operation.provisional.Reader;
import com.ameri.operation.services.ManagerReportService;
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

@WebServlet("/reports/manager-reports")
public class ManagerReport extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

                    List<AdminBeans> topCommentsList = GetAdminBeansList.getMagazineTopCommentsList(startDateStr, endDateStr);
                    resp.getWriter().write(new AdminBeansConverter(AdminBeans.class).toJson(topCommentsList));
                    break;
                case "subscriptions":

                    List<AdminBeans> topPopularListList = GetAdminBeansList.getMagazineTopPopularList(startDateStr, endDateStr);
                    resp.getWriter().write(new AdminBeansConverter(AdminBeans.class).toJson(topPopularListList));
                    break;
                case "gains":
                    List<GananciaBeans> gananciaBeans = GetAdminBeansList.getGananciaBeansList(startDateStr,endDateStr);
                    resp.getWriter().write(new GananciaBeansConverter(GananciaBeans.class).toJson(gananciaBeans));
                    break;
                default:
                    break;
            }


        } catch (SQLException e) {
            // manejar la excepcion
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ManagerReportService reportService = null;

        resp.setContentType("application/pdf");
        resp.setHeader("Content-disposition", "attachment; filename=report.pdf");

        String reportType = req.getParameter("reportType");

        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        System.out.println(body);
        ProvisionalBeanConverter converter= new ProvisionalBeanConverter(ProvisionalBean.class);

        ProvisionalBean provisionalBean = converter.fromJson(body);


        try {

            switch (reportType){
                case "comments":
                    reportService = new ManagerReportService();
                    reportService.printMagazineTopComments(resp.getOutputStream(), provisionalBean);
                    break;
                case "subscriptions":
                    reportService = new ManagerReportService();
                    reportService.printMagazineTopSubscriptions(resp.getOutputStream(), provisionalBean);
                    break;
                case "gains":
                    reportService = new ManagerReportService();
                    reportService.printMagazineGainsReport(resp.getOutputStream(), provisionalBean);
                    break;
                default:
                    break;
            }


        } catch (IOException | JRException | SQLException e) {
            // manejar la excepcion
            e.printStackTrace();
        }
    }
}
