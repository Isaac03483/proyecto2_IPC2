package com.ameri.objects.interfaces.user.editor;

import com.ameri.objects.classes.user.editor.EditorTag;
import com.ameri.objects.classes.user.editor.Profile;

import java.sql.SQLException;
import java.util.List;

public interface DAOEditorTag {

    void insert(EditorTag editorTag) throws SQLException;

    void update(EditorTag editorTag) throws SQLException;

    void delete(EditorTag editorTag) throws SQLException;

    List<EditorTag> list(Profile profile)  throws SQLException;
}
