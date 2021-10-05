package com.ameri.objects.interfaces.user.manager;

import com.ameri.objects.classes.user.manager.Category;

import java.sql.SQLException;
import java.util.List;

public interface DAOCategory {

    void insert(Category category) throws SQLException;

    void update(Category category) throws SQLException;

    void delete(Category category) throws SQLException;

    List<Category> list() throws SQLException;
}
