package com.ameri.objects.interfaces.user.editor;

import com.ameri.objects.classes.user.User;
import com.ameri.objects.classes.user.editor.Profile;

import java.sql.SQLException;
import java.util.List;

public interface DAOProfile {

    void insert(Profile profile) throws SQLException;

    void updateImage(Profile profile) throws SQLException;

    void updateInf(Profile profile) throws  SQLException;

    void delete(Profile profile) throws SQLException;

    List<Profile> listAllProfiles() throws SQLException;

    Profile listWhereEditorName(User user) throws SQLException;
}
