package com.ameri.dao.user.editor;

import com.ameri.database.Connector;
import com.ameri.objects.classes.user.editor.EditorAccount;
import com.ameri.objects.interfaces.user.editor.DAOEditorAccount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOEditorAccountImpl implements DAOEditorAccount {

    private final String INSERT_ACCOUNT = "INSERT INTO cuenta_editor (nombre_editor, nombre_suscriptor,registro_revista, total_pagar, costo_descuento, ganancia, fecha_pago) VALUES (?,?,?,?,?,?,?)";
    private final String GET_ACCOUNT_BETWEEN = "SELECT * FROM cuenta_editor WHERE registro_revista=? AND fecha_pago BETWEEN ? AND ?";
    private final String GET_ACCOUNT = "SELECT * FROM cuenta_editor WHERE registro_revista=?";

    public DAOEditorAccountImpl(){
        new Connector();
    }

    @Override
    public void insert(EditorAccount editorAccount) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_ACCOUNT);
        query.setString(1,editorAccount.getEditorName());
        query.setString(2,editorAccount.getSubscriberName());
        query.setInt(3,editorAccount.getMagazineRecord());
        query.setBigDecimal(4,editorAccount.getTotalPay());
        query.setBigDecimal(5,editorAccount.getDescuento());
        query.setBigDecimal(6,editorAccount.getGanancia());
        query.setDate(7,editorAccount.getPayDate());
        query.executeUpdate();
    }

    @Override
    public void update(EditorAccount editorAccount) throws SQLException {

    }

    @Override
    public void delete(EditorAccount editorAccount) throws SQLException {

    }

    @Override
    public List<EditorAccount> listWhereMagazineRecord(int magazineRecord) throws SQLException {
        List<EditorAccount> editorAccountList = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_ACCOUNT);
        query.setInt(1, magazineRecord);
        return getEditorAccountList(editorAccountList, query);
    }

    private List<EditorAccount> getEditorAccountList(List<EditorAccount> editorAccountList, PreparedStatement query) throws SQLException {
        ResultSet resultSet = query.executeQuery();

        while (resultSet.next()){

            editorAccountList.add(new EditorAccount(resultSet.getInt("registro_cuenta"),resultSet.getString("nombre_editor"),resultSet.getString("nombre_suscriptor"),resultSet.getInt("registro_revista"),resultSet.getBigDecimal("total_pagar"),resultSet.getBigDecimal("costo_descuento"), resultSet.getBigDecimal("ganancia"),String.valueOf(resultSet.getDate("fecha_pago"))));
        }

        return editorAccountList;
    }

    @Override
    public List<EditorAccount> listWhereMagazineRecordBetween(int magazineRecord, LocalDate start, LocalDate end) throws SQLException {
        List<EditorAccount> editorAccountList = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_ACCOUNT_BETWEEN);
        query.setInt(1, magazineRecord);
        query.setString(2, start.toString());
        query.setString(3,end.toString());
        return getEditorAccountList(editorAccountList, query);
    }
}
