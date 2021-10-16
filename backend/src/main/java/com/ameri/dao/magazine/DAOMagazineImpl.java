package com.ameri.dao.magazine;

import com.ameri.database.Connector;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.manager.Category;
import com.ameri.objects.enums.magazine.MagazineComment;
import com.ameri.objects.enums.magazine.MagazineLike;
import com.ameri.objects.enums.magazine.MagazineStatus;
import com.ameri.objects.enums.magazine.MagazineSubscription;
import com.ameri.objects.interfaces.magazine.DAOMagazine;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DAOMagazineImpl implements DAOMagazine {

    private final String INSERT_MAGAZINE = "INSERT INTO revista (nombre_editor,nombre_revista, archivo, fecha_publicacion, descripcion, nombre_categoria, costo_suscripcion,estado_revista, like_revista, comentario, suscripcion) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_MAGAZINE = "UPDATE revista SET nombre_revista=?,archivo=?,descripcion=?, nombre_categoria=?, costo_publicacion=? WHERE registro_revista=?";
    private final String UPDATE_INFO = "UPDATE revista SET fecha_aceptacion =?, estado_revista =?, costo_por_dia =?, fecha_modificacion_cpd =? WHERE registro_revista=?";
    private final String GET_EDITORS_MAGAZINE = "SELECT * FROM revista WHERE nombre_editor =?";
    private final String GET_MAGAZINE = "SELECT * FROM revista WHERE nombre_editor = ? AND nombre_revista =?";
    public DAOMagazineImpl(){new Connector();}

    @Override
    public void insert(Magazine magazine) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_MAGAZINE);
        query.setString(1,magazine.getEditorName());
        query.setString(2,magazine.getMagazineName());
        query.setBytes(3,magazine.getFile());
        query.setDate(4,magazine.getPublicationDate());
        query.setString(5,magazine.getDescription());
        query.setString(6,magazine.getCategory().getCategoryName().toLowerCase());
        query.setBigDecimal(7, magazine.getSubscriptionCost());
        query.setString(8,magazine.getStatus().getStatus());
        query.setString(9, magazine.getLike().getStatus());
        query.setString(10, magazine.getComment().getStatus());
        query.setString(11, magazine.getSubscription().getStatus());
        query.executeUpdate();
    }

    @Override
    public void update(Magazine magazine) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_MAGAZINE);
        query.setString(1,magazine.getMagazineName());
        query.setBytes(2,magazine.getFile());
        query.setString(3,magazine.getDescription());
        query.setString(4,magazine.getCategory().getCategoryName().toLowerCase());
        query.setBigDecimal(5, magazine.getSubscriptionCost());
        query.setInt(6,magazine.getMagazineRecord());
        query.executeUpdate();
    }

    @Override
    public void updateInf(Magazine magazine) throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_INFO);
        query.setDate(1, magazine.getAcceptDate());
        query.setString(2, magazine.getStatus().getStatus());
        query.setBigDecimal(3, magazine.getDayCost());
        query.setDate(4, magazine.getUpdateDate());
        query.setInt(5, magazine.getMagazineRecord());
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
        List<Magazine> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_EDITORS_MAGAZINE);
        query.setString(1, profile.getEditorName());
        ResultSet resultSet = query.executeQuery();

        while (resultSet.next()){
            list.add(new Magazine(resultSet.getInt("registro_revista"), resultSet.getString("nombre_editor"), resultSet.getString("nombre_revista"), resultSet.getBytes("archivo"), resultSet.getString("fecha_publicacion"),
                    resultSet.getString("descripcion"), new Category(resultSet.getString("nombre_categoria")), resultSet.getBigDecimal("costo_suscripcion"), MagazineStatus.value(resultSet.getString("estado_revista")),
                    resultSet.getString("fecha_aceptacion"), resultSet.getBigDecimal("costo_por_dia"), resultSet.getString("fecha_modificacion_cpd"), MagazineLike.value(resultSet.getString("like_revista")),
                    MagazineComment.value(resultSet.getString("comentario")), MagazineSubscription.value(resultSet.getString("suscripcion")))
            );
        }

        return list;
    }

    @Override
    public List<Magazine> listMagazinesWhereName(Magazine magazine) throws SQLException {
        return null;
    }

    @Override
    public List<Magazine> listMagazinesWhereCategory(Category category) throws SQLException {
        return null;
    }

    @Override
    public Magazine getMagazine(Magazine magazine) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_MAGAZINE);
        query.setString(1, magazine.getEditorName());
        query.setString(2, magazine.getMagazineName());
        ResultSet resultSet = query.executeQuery();

        if(resultSet.next()){
            return new Magazine(resultSet.getInt("registro_revista"), resultSet.getString("nombre_editor"), resultSet.getString("nombre_revista"), resultSet.getBytes("archivo"),String.valueOf(resultSet.getDate("fecha_publicacion")), resultSet.getString("descripcion"), new Category(resultSet.getString("categoria")), resultSet.getBigDecimal("costo_suscripcion"),
                    MagazineStatus.value(resultSet.getString("estado_revista")), resultSet.getString("fecha_aceptacion"), new BigDecimal(resultSet.getString("costo_por_dia")), resultSet.getString("fecha_modificacion_cpd"), MagazineLike.value(resultSet.getString("like_revista")), MagazineComment.value(resultSet.getString("comentario")), MagazineSubscription.value(resultSet.getString("suscripcion")));
        }

        return null;
    }
}
