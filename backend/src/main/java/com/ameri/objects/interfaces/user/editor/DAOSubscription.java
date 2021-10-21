package com.ameri.objects.interfaces.user.editor;

import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.editor.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface DAOSubscription {

    void insert(Subscription subscription) throws SQLException;

    void updateSubscriptionInf(Subscription subscription) throws SQLException;

    void updateLike(Subscription subscription) throws  SQLException;

    void updateSubscriptionStatus() throws  SQLException;

    void delete(Subscription subscription) throws SQLException;

    List<Subscription> listAllSubscription(Magazine magazine) throws SQLException;

    List<Subscription> listWhereSubscriberName(Profile profile) throws SQLException;

    Subscription getSubscription(Subscription subscription) throws SQLException;

    Subscription getSubscriptionLikes(int magazineRecord) throws SQLException;
}
