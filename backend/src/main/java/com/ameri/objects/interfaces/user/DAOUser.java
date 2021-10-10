package com.ameri.objects.interfaces.user;

import com.ameri.objects.classes.user.User;

import java.sql.SQLException;
import java.util.List;

public interface DAOUser {

    void insert(User user) throws SQLException;

    void updateUserName(User user) throws SQLException;

    void updateUserPass(User user) throws  SQLException;

    void delete(User user) throws SQLException;

    List<User> list() throws  SQLException;

    User getUser(User user) throws SQLException;
}
