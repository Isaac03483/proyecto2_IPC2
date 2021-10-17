package com.ameri.dao.magazine;

import com.ameri.database.Connector;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.magazine.MagazineTag;
import com.ameri.objects.interfaces.magazine.DAOMagazineTag;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOMagazineTagImpl implements DAOMagazineTag {

    private final String INSERT_MAGAZINE_TAG = "INSERT INTO etiqueta_revista (registro_revista, nombre_etiqueta) VALUES (?,?)";
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
    public void delete(MagazineTag tag) throws SQLException {

    }

    @Override
    public void listMagazineTags(Magazine magazine) throws SQLException {

    }
}
