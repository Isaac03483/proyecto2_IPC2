package com.ameri.objects.interfaces.user.manager;

import com.ameri.objects.classes.user.User;
import com.ameri.objects.classes.user.manager.Manager;
import java.sql.SQLException;
import java.util.List;

public interface DAOManager {

    void insert(Manager manager) throws SQLException;

    void update(Manager manager) throws SQLException;

    void delete(Manager manager) throws SQLException;

    List<Manager> list() throws SQLException;

    Manager selectManager(Manager manager) throws SQLException;
}
