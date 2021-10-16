package com.ameri.dao.user.editor;

import com.ameri.database.Connector;
import com.ameri.objects.classes.user.User;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.interfaces.user.editor.DAOProfile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOProfileImpl implements DAOProfile {

    private final String INSERT_PROFILE = "INSERT INTO perfil (nombre_editor) VALUES (?)";
    private final String UPDATE_PROFILE = "UPDATE perfil SET hobby=?, descripcion=?, gustos=? WHERE nombre_editor=?";
    private final String UPDATE_IMAGE = "UPDATE perfil SET foto=? WHERE nombre_editor =?";
    private final String GET_PROFILE_WHERE = "SELECT * FROM perfil WHERE nombre_editor =?";

    public DAOProfileImpl(){
        new Connector();
    }

    @Override
    public void insert(Profile profile) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_PROFILE);
        query.setString(1, profile.getEditorName());
        query.executeUpdate();
    }

    @Override
    public void updateImage(Profile profile) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_IMAGE);
        query.setBytes(1, profile.getImage());
        query.setString(2, profile.getEditorName());
        query.executeUpdate();
    }

    @Override
    public void updateInf(Profile profile) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_PROFILE);
        query.setString(1,profile.getHobby());
        query.setString(2, profile.getDescription());
        query.setString(3, profile.getLikes());
        query.setString(4, profile.getEditorName());
        query.executeUpdate();
    }

    @Override
    public void delete(Profile profile) throws SQLException {

    }

    @Override
    public List<Profile> listAllProfiles() throws SQLException {
        return null;
    }

    @Override
    public Profile listWhereEditorName(User user) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_PROFILE_WHERE);
        query.setString(1, user.getUserName());
        ResultSet resultSet = query.executeQuery();

        if(resultSet.next()){
            return new Profile(resultSet.getString("nombre_editor"), resultSet.getBytes("foto"), resultSet.getString("hobby"), resultSet.getString("descripcion"), resultSet.getString("gustos"));
        }
        return null;
    }
}
