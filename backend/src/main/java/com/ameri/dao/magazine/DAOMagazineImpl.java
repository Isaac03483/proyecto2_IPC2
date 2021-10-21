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

    private final String INSERT_MAGAZINE = "INSERT INTO revista (nombre_editor,nombre_revista, archivo, fecha_publicacion, descripcion, nombre_categoria, costo_suscripcion, like_revista, comentario, suscripcion, estado_revista) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_MAGAZINE = "UPDATE revista SET nombre_revista=?,descripcion=?, nombre_categoria=?, costo_suscripcion=?, like_revista=?, comentario=?, suscripcion=? WHERE registro_revista=?";
    private final String UPDATE_INFO = "UPDATE revista SET fecha_aceptacion =?, estado_revista =?, costo_por_dia =?, fecha_modificacion_cpd =? WHERE registro_revista=?";
    private final String GET_EDITORS_MAGAZINE = "SELECT * FROM revista WHERE nombre_editor =?";
    private final String GET_ALL_MAGAZINES= "SELECT * FROM revista";
    private final String GET_MAGAZINE_NAME_CATEGORY = "SELECT * FROM revista WHERE nombre_revista LIKE ? AND nombre_categoria=? AND estado_revista='ACEPTADA' AND nombre_editor != ?";
    private final String GET_MAGAZINE_NAME = "SELECT * FROM revista WHERE nombre_revista LIKE ? AND estado_revista='ACEPTADA' AND nombre_editor != ?";
    private final String GET_MAGAZINE_WAITING = "SELECT * FROM revista WHERE estado_revista =?";
    private final String GET_MAGAZINE = "SELECT * FROM revista WHERE nombre_editor = ? AND nombre_revista =?";
    private final String GET_MAGAZINE_WITH_RECORD = "SELECT * FROM revista WHERE registro_revista=?";
    private final String DELETE_MAGAZINE = "DELETE FROM revista WHERE registro_revista=?";

    public DAOMagazineImpl(){new Connector();}

    @Override
    public void insert(Magazine magazine) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_MAGAZINE);
        query.setString(1,magazine.getEditorName());
        query.setString(2,magazine.getMagazineName());
        query.setString(3,magazine.getFile());
        query.setDate(4,magazine.getPublicationDate());
        query.setString(5,magazine.getDescription());
        query.setString(6,magazine.getCategory().getCategoryName().toLowerCase());
        query.setBigDecimal(7, magazine.getSubscriptionCost());
        query.setString(8, magazine.getLike().getStatus());
        query.setString(9, magazine.getComment().getStatus());
        query.setString(10, magazine.getSubscription().getStatus());
        query.setString(11, magazine.getStatus().getStatus());
        query.executeUpdate();
    }

    @Override
    public void update(Magazine magazine) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_MAGAZINE);
        query.setString(1,magazine.getMagazineName());
        query.setString(2,magazine.getDescription());
        query.setString(3,magazine.getCategory().getCategoryName().toLowerCase());
        query.setBigDecimal(4, magazine.getSubscriptionCost());
        query.setString(5,magazine.getLike().getStatus());
        query.setString(6,magazine.getComment().getStatus());
        query.setString(7,magazine.getSubscription().getStatus());
        query.setInt(8,magazine.getMagazineRecord());
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

        PreparedStatement query = Connector.getConnection().prepareStatement(DELETE_MAGAZINE);
        query.setInt(1,magazine.getMagazineRecord());
        query.executeUpdate();
    }

    @Override
    public List<Magazine> listAllMagazines() throws SQLException {
        List<Magazine> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_ALL_MAGAZINES);
        return getMagazineList(list, query);
    }

    @Override
    public List<Magazine> listEditorMagazines(Profile profile) throws SQLException {
        List<Magazine> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_EDITORS_MAGAZINE);
        query.setString(1, profile.getEditorName());
        return getMagazineList(list, query);
    }

    @Override
    public List<Magazine> listMagazinesWhereName(Magazine magazine) throws SQLException {
        List<Magazine> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_MAGAZINE_NAME);
        query.setString(1, magazine.getMagazineName());
        query.setString(2, magazine.getEditorName());
        return getMagazineList(list, query);
    }

    @Override
    public List<Magazine> listMagazinesWhereCategory(Magazine magazine) throws SQLException {
        List<Magazine> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_MAGAZINE_NAME_CATEGORY);
        query.setString(1, magazine.getMagazineName());
        query.setString(2, magazine.getCategory().getCategoryName());
        query.setString(3, magazine.getEditorName());
        return getMagazineList(list, query);
    }

    private List<Magazine> getMagazineList(List<Magazine> list, PreparedStatement query) throws SQLException {
        ResultSet resultSet = query.executeQuery();

        while (resultSet.next()){
            list.add(new Magazine(resultSet.getInt("registro_revista"), resultSet.getString("nombre_editor"), resultSet.getString("nombre_revista"), resultSet.getString("archivo"), resultSet.getString("fecha_publicacion"),
                    resultSet.getString("descripcion"), new Category(resultSet.getString("nombre_categoria")), resultSet.getBigDecimal("costo_suscripcion"), MagazineStatus.value(resultSet.getString("estado_revista")),
                    resultSet.getString("fecha_aceptacion"), resultSet.getBigDecimal("costo_por_dia"), resultSet.getString("fecha_modificacion_cpd"), MagazineLike.value(resultSet.getString("like_revista")),
                    MagazineComment.value(resultSet.getString("comentario")), MagazineSubscription.value(resultSet.getString("suscripcion")))
            );
        }

        return list;
    }

    @Override
    public List<Magazine> listMagazinesWhereStatus(String status) throws SQLException {
        List<Magazine> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_MAGAZINE_WAITING);
        query.setString(1,status);
        return getMagazineList(list, query);
    }

    @Override
    public Magazine getMagazine(Magazine magazine) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_MAGAZINE);
        query.setString(1, magazine.getEditorName());
        query.setString(2, magazine.getMagazineName());
        ResultSet resultSet = query.executeQuery();

        return getMagazine(resultSet);
    }

    @Override
    public Magazine getMagazineWithRecord(Magazine magazine) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_MAGAZINE_WITH_RECORD);
        query.setInt(1, magazine.getMagazineRecord());
        ResultSet resultSet = query.executeQuery();

        return getMagazine(resultSet);
    }

    private Magazine getMagazine(ResultSet resultSet) throws SQLException {
        if(resultSet.next()){
            return new Magazine(resultSet.getInt("registro_revista"), resultSet.getString("nombre_editor"), resultSet.getString("nombre_revista"), resultSet.getString("archivo"),String.valueOf(resultSet.getDate("fecha_publicacion")), resultSet.getString("descripcion"), new Category(resultSet.getString("nombre_categoria")), resultSet.getBigDecimal("costo_suscripcion"),
                    MagazineStatus.value(resultSet.getString("estado_revista")), resultSet.getString("fecha_aceptacion"), new BigDecimal(resultSet.getString("costo_por_dia")), resultSet.getString("fecha_modificacion_cpd"), MagazineLike.value(resultSet.getString("like_revista")), MagazineComment.value(resultSet.getString("comentario")), MagazineSubscription.value(resultSet.getString("suscripcion")));
        }

        return null;
    }
}
