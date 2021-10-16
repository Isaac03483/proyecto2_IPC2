package com.ameri.objects.interfaces.user.manager;

import com.ameri.objects.classes.user.manager.Tag;

import java.sql.SQLException;
import java.util.List;

public interface DAOTag {

    void insert(Tag tag) throws SQLException;

    void update(Tag tag) throws SQLException;

    void delete(Tag tag) throws SQLException;

    List<Tag> list() throws SQLException;

    Tag getTag(Tag tag) throws SQLException;
}
