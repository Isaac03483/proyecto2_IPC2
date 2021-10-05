package com.ameri.dao.magazine;

import com.ameri.database.Connector;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.manager.Category;
import com.ameri.objects.interfaces.magazine.DAOMagazine;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAOMagazineImpl implements DAOMagazine {

    private final String INSERT_MAGAZINE = "INSERT INTO revista (nombre_editor,nombre_revista, archivo, fecha_publicacion, descripcion, categoria, costo_suscripcion) VALUES (?,?,?,?,?,?,?)";
    private final String UPDATE_MAGAZINE = "UPDATE revista SET nombre_revista=?,archivo=?,fecha_publicacion=?,descripcion=?, categoria=?, costo_publicacion=? WHERE registro_revista=?";

    public DAOMagazineImpl(){
        new Connector();
    }

    @Override
    public void insert(Magazine magazine) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_MAGAZINE);
        query.setString(1,magazine.getEditorName());
        query.setString(2,magazine.getMagazineName());
        query.setByte(3,magazine.getFile());
        query.setDate(4,magazine.getPublicationDate());
        query.setString(5,magazine.getDescription());
        query.setString(6,magazine.getCategory().getCategoryName());
        query.setBigDecimal(7, magazine.getSubscriptionCost());
        query.executeUpdate();
    }

    @Override
    public void update(Magazine magazine) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_MAGAZINE);
        query.setString(1,magazine.getMagazineName());
        query.setByte(2,magazine.getFile());
        query.setDate(3,magazine.getPublicationDate());
        query.setString(4,magazine.getDescription());
        query.setString(5,magazine.getCategory().getCategoryName());
        query.setBigDecimal(6, magazine.getSubscriptionCost());
        query.setInt(7,magazine.getMagazineRecord());
        query.executeUpdate();
    }

    @Override
    public void delete(Magazine magazine) throws SQLException {

    }

    @Override
    public List<Magazine> listAllMagazines() throws SQLException {
        return null;
    }

    @Override
    public List<Magazine> listEditorMagazines(Profile profile) throws SQLException {
        return null;
    }

    @Override
    public List<Magazine> listMagazinesWhereName(Magazine magazine) throws SQLException {
        return null;
    }

    @Override
    public List<Magazine> listMagazinesWhereCategory(Category category) throws SQLException {
        return null;
    }
}
