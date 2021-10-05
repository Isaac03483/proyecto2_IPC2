package com.ameri.dao.user.editor;

import com.ameri.database.Connector;
import com.ameri.objects.classes.user.editor.EditorTag;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.interfaces.user.editor.DAOEditorTag;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAOEditorTagImpl implements DAOEditorTag {

    private final String INSERT_TAG = "INSERT INTO etiqueta_editor(nombre_editor,nombre_etiqueta) VALUES (?,?)";
    private final String UPDATE_TAG = "UPDATE etiqueta_editor SET nombre_etiqueta = ? WHERE nombre_editor=?";
    private final String DELETE_TAG ="DELETE FROM etiqueta_editor WHERE nombre_editor=?";

    public DAOEditorTagImpl(){
        Connector connector = new Connector();
    }

    @Override
    public void insert(EditorTag editorTag) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_TAG);
        query.setString(1,editorTag.getEditorName());
        query.setString(2,editorTag.getTagName());
        query.executeUpdate();
    }

    @Override
    public void update(EditorTag editorTag) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_TAG);
        query.setString(1,editorTag.getTagName());
        query.setString(2,editorTag.getEditorName());
        query.executeUpdate();
    }

    @Override
    public void delete(EditorTag editorTag) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(DELETE_TAG);
        query.setString(1,editorTag.getEditorName());
        query.executeUpdate();
    }

    @Override
    public List<EditorTag> list(Profile profile) throws SQLException {
        return null;
    }
}
