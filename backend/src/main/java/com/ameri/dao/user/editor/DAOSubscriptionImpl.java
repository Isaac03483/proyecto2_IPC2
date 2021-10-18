package com.ameri.dao.user.editor;

import com.ameri.database.Connector;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.editor.Subscription;
import com.ameri.objects.interfaces.user.editor.DAOSubscription;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAOSubscriptionImpl implements DAOSubscription {

    private final String INSERT_SUBSCRIPTION = "INSERT INTO suscripcion (nombre_suscriptor, registro_revista, total_pago, intervalo_pago, fecha_registro, fecha_fin,estado_suscripcion,like_suscripcion) VALUES (?,?,?,?,?,?,?,?)";
    private final String UPDATE_SUBSCRIPTION = "UPDATE suscripcion SET total_pago=?, intervalo_pago=?,estado_suscripcion=?,like_suscripcion=?, fecha_fin=? WHERE registro_suscripcion=?";

    public DAOSubscriptionImpl(){
        new Connector();
    }

    @Override
    public void insert(Subscription subscription) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(INSERT_SUBSCRIPTION);
        query.setString(1,subscription.getSubscriberName());
        query.setInt(2,subscription.getMagazineRecord());
        query.setBigDecimal(3,subscription.getTotalPay());
        query.setString(4,subscription.getPaymentInterval().getInterval());
        query.setDate(5,subscription.getRecordDate());
        query.setDate(6,subscription.getEndDate());
        query.setString(7,subscription.getSubscriptionStatus().getStatus());
        query.setString(8,subscription.getSubscriptionLike().getStatus());
        query.executeUpdate();
    }

    @Override
    public void update(Subscription subscription) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_SUBSCRIPTION);
        query.setBigDecimal(1,subscription.getTotalPay());
        query.setString(2,subscription.getPaymentInterval().getInterval());
        query.setString(3,subscription.getSubscriptionStatus().getStatus());
        query.setDate(4,subscription.getEndDate());
        query.setString(5,subscription.getSubscriptionLike().getStatus());
        query.executeUpdate();
    }

    @Override
    public void delete(Subscription subscription) throws SQLException {

    }

    @Override
    public List<Subscription> listAllSubscription() throws SQLException {
        return null;
    }

    @Override
    public List<Subscription> listWhereSubscriberName(Profile profile) throws SQLException {
        return null;
    }
}
