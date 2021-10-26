package com.ameri.objects.interfaces.user.editor;

import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.editor.Subscription;

import java.sql.SQLException;
import java.time.LocalDate;
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

    int getSubscriptionNumberLikes(int magazineRecord) throws SQLException;

    int getSubscriptionNumberLikesBetween(int magazineRecord, LocalDate start, LocalDate end) throws SQLException;

    List<Subscription> getListSubscriptionsBetween(int magazineRecord, LocalDate start, LocalDate end) throws SQLException;

    List<Subscription> getListSubscriptionsLikesBetween(int magazineRecord, LocalDate start, LocalDate end) throws SQLException;

    List<Subscription> getListSubscriptions(int magazineRecord) throws SQLException;


    List<Subscription> listWhereSubscriptionLike(int magazineRecord) throws SQLException;
}
