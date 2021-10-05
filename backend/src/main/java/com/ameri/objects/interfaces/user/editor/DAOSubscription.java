package com.ameri.objects.interfaces.user.editor;

import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.editor.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface DAOSubscription {

    void insert(Subscription subscription) throws SQLException;

    void update(Subscription subscription) throws SQLException;

    void delete(Subscription subscription) throws SQLException;

    List<Subscription> listAllSubscription() throws SQLException;

    List<Subscription> listWhereSubscriberName(Profile profile) throws SQLException;
}
