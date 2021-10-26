package com.ameri.servlets.user.editor.subscription;

import com.ameri.converter.user.editor.SubscriptionConverter;
import com.ameri.dao.user.editor.DAOSubscriptionImpl;
import com.ameri.operation.Reader;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.editor.Subscription;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/add-subscription")
public class AddSubscription extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String editorName = req.getParameter("editorName");
        Profile profile = new Profile(editorName);

        try {
            DAOSubscriptionImpl dao = new DAOSubscriptionImpl();
            dao.updateSubscriptionStatus();
            List<Subscription> list = dao.listWhereSubscriberName(profile);
            resp.getWriter().write(new SubscriptionConverter(Subscription.class).toJson(list));
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
            DAOSubscriptionImpl dao = new DAOSubscriptionImpl();

            if(dao.getSubscription(subscription) == null){
                dao.insert(subscription);
                resp.getWriter().write(converter.toJson(dao.getSubscription(subscription)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
