package com.ameri.servlets.user.editor.subscription;

import com.ameri.converter.user.editor.SubscriptionConverter;
import com.ameri.dao.user.editor.DAOSubscriptionImpl;
import com.ameri.operation.provisional.Reader;
import com.ameri.objects.classes.user.editor.Subscription;
import com.ameri.objects.enums.user.editor.SubscriptionLike;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-subscription-information")
public class UpdateSubscriptionInf extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int subscriptionRecord = Integer.parseInt(req.getParameter("subscriptionRecord"));
        SubscriptionLike subscriptionLike = SubscriptionLike.value(req.getParameter("like"));

        Subscription subscription = new Subscription(subscriptionRecord,subscriptionLike);

        try {
            new DAOSubscriptionImpl().updateLike(subscription);
            resp.getWriter().write(new SubscriptionConverter(Subscription.class).toJson(subscription));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        String body = new Reader(reader).getInformation();

        SubscriptionConverter converter = new SubscriptionConverter(Subscription.class);

        Subscription subscription = converter.fromJson(body);

        try {
            new DAOSubscriptionImpl().updateSubscriptionInf(subscription);
            resp.getWriter().write(converter.toJson(subscription));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
