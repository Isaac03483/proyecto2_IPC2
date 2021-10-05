package com.ameri.objects.interfaces.user.editor;

import com.ameri.objects.classes.user.editor.EditorAccount;
import com.ameri.objects.classes.user.editor.Profile;

import java.sql.SQLException;
import java.util.List;

public interface DAOEditorAccount {

    void insert(EditorAccount editorAccount) throws SQLException;

    void update(EditorAccount editorAccount) throws SQLException;

    void delete(EditorAccount editorAccount) throws SQLException;

    List<EditorAccount> list() throws SQLException;

    List<EditorAccount> listWhereEditorName(Profile profile) throws SQLException;

}
