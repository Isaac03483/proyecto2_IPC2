package com.ameri.dao.magazine;

import com.ameri.database.Connector;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.magazine.MagazineTag;
import com.ameri.objects.interfaces.magazine.DAOMagazineTag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOMagazineTagImpl implements DAOMagazineTag {

    private final String INSERT_MAGAZINE_TAG = "INSERT INTO etiqueta_revista (registro_revista, nombre_etiqueta) VALUES (?,?)";
    private final String DELETE_MAGAZINE_TAG = "DELETE FROM etiqueta_revista WHERE registro_revista=?";
    private final String GET_MAGAZINE_TAGS = "SELECT * FROM etiqueta_revista WHERE registro_revista=?";

    public DAOMagazineTagImpl() {
        new Connector();
    }

    @Override
    public void insert(MagazineTag tag) throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_MAGAZINE_TAG);
        query.setInt(1, tag.getMagazineRecord());
        query.setString(2, tag.getTagName());
        query.executeUpdate();

    }

    @Override
    public void update(MagazineTag tag) throws SQLException {

    }

    @Override
    public void delete(Magazine magazine) throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(DELETE_MAGAZINE_TAG);
        query.setInt(1, magazine.getMagazineRecord());
        query.executeUpdate();
    }

    @Override
    public List<MagazineTag> listMagazineTags(Magazine magazine) throws SQLException {
        List<MagazineTag> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_MAGAZINE_TAGS);
        query.setInt(1, magazine.getMagazineRecord());
        ResultSet resultSet = query.executeQuery();

        while (resultSet.next()){
            list.add(new MagazineTag(resultSet.getInt("registro_revista"), resultSet.getString("nombre_etiqueta")));

        }

        return  list;
    }
}
