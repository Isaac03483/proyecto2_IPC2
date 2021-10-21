package com.ameri.dao.user.editor;

import com.ameri.database.Connector;
import com.ameri.objects.classes.user.editor.EditorAccount;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.interfaces.user.editor.DAOEditorAccount;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAOEditorAccountImpl implements DAOEditorAccount {

    private final String INSERT_ACCOUNT = "INSERT INTO cuenta_editor (nombre_editor, nombre_suscriptor,registro_revista, total_pagar, costo_descuento, ganancia, fecha_pago) VALUES (?,?,?,?,?,?,?)";

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
    public List<EditorAccount> list() throws SQLException {
        return null;
    }

    @Override
    public List<EditorAccount> listWhereEditorName(Profile profile) throws SQLException {
        return null;
    }
}
