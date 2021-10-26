package com.ameri.dao.user.editor;

import com.ameri.database.Connector;
import com.ameri.objects.classes.magazine.Magazine;
import com.ameri.objects.classes.user.editor.Profile;
import com.ameri.objects.classes.user.editor.Subscription;
import com.ameri.objects.enums.user.editor.PaymentEnum;
import com.ameri.objects.enums.user.editor.SubscriptionLike;
import com.ameri.objects.enums.user.editor.SubscriptionStatus;
import com.ameri.objects.interfaces.user.editor.DAOSubscription;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOSubscriptionImpl implements DAOSubscription {

    private final String INSERT_SUBSCRIPTION = "INSERT INTO suscripcion (nombre_suscriptor, registro_revista, total_pago, intervalo_pago, fecha_registro, fecha_fin,estado_suscripcion,like_suscripcion, nombre_revista) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_SUBSCRIPTION_INF = "UPDATE suscripcion SET total_pago=?, intervalo_pago=?,estado_suscripcion=?, fecha_fin=? WHERE registro_suscripcion=?";
    private final String UPDATE_SUBSCRIPTION_LIKE = "UPDATE suscripcion SET like_suscripcion =? WHERE registro_suscripcion=?";
    private final String UPDATE_SUBSCRIPTION_STATUS = "UPDATE suscripcion SET estado_suscripcion = 'CANCELADO' WHERE fecha_fin<CURDATE() AND estado_suscripcion = 'VIGENTE'";
    private final String GET_EDITOR_SUBSCRIPTIONS = "SELECT * FROM suscripcion WHERE nombre_suscriptor=?";
    private final String GET_SUBSCRIPTION = "SELECT * FROM suscripcion WHERE registro_revista=? AND nombre_suscriptor =?";
    private final String GET_MAGAZINE_SUBSCRIPTIONS = "SELECT * FROM suscripcion WHERE registro_revista=?";
    private final String DELETE_SUBSCRIPTION = "DELETE FROM suscripcion WHERE registro_suscripcion =?";
    private final String GET_SUBSCRIPTION_LIKES = "SELECT COUNT(*) as likes FROM suscripcion WHERE registro_revista=? AND like_suscripcion='SI'";
    private final String GET_SUBSCRIPTION_LIKES_BETWEEN = "SELECT COUNT(*) as likes FROM suscripcion WHERE registro_revista=? AND like_suscripcion='SI' AND fecha_registro BETWEEN ? AND ?";
    private final String GET_SUBSCRIPTION_WHERE_LIKE = "SELECT * FROM suscripcion WHERE registro_revista=? AND like_suscripcion='SI'";
    private final String GET_SUBSCRIPTION_WHERE_LIKES_BETWEEN = "SELECT * FROM suscripcion WHERE registro_revista=? AND like_suscripcion ='SI' AND fecha_registro BETWEEN ? AND ?";
    private final String GET_SUBSCRIPTIONS_BETWEEN = "SELECT * FROM suscripcion WHERE registro_revista=? AND fecha_registro BETWEEN ? AND ? AND estado_suscripcion = 'VIGENTE'";
    private final String GET_SUBSCRIPTIONS = "SELECT * FROM suscripcion WHERE registro_revista=? AND estado_suscripcion = 'VIGENTE'";


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
        query.setString(9, subscription.getMagazineName());
        query.executeUpdate();
    }

    @Override
    public void updateSubscriptionInf(Subscription subscription) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_SUBSCRIPTION_INF);
        query.setBigDecimal(1,subscription.getTotalPay());
        query.setString(2,subscription.getPaymentInterval().getInterval());
        query.setString(3,subscription.getSubscriptionStatus().getStatus());
        query.setDate(4,subscription.getEndDate());
        query.setInt(5, subscription.getSubscriptionRecord());
        query.executeUpdate();
    }

    @Override
    public void updateLike(Subscription subscription) throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_SUBSCRIPTION_LIKE);
        query.setString(1, subscription.getSubscriptionLike().getStatus());
        query.setInt(2,subscription.getSubscriptionRecord());
        query.executeUpdate();
    }

    @Override
    public void updateSubscriptionStatus() throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(UPDATE_SUBSCRIPTION_STATUS);
        query.executeUpdate();
    }

    @Override
    public void delete(Subscription subscription) throws SQLException {

        PreparedStatement query = Connector.getConnection().prepareStatement(DELETE_SUBSCRIPTION);
        query.setInt(1,subscription.getSubscriptionRecord());
        query.executeUpdate();
    }

    @Override
    public List<Subscription> listAllSubscription(Magazine magazine) throws SQLException {
        List<Subscription> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_MAGAZINE_SUBSCRIPTIONS);
        query.setInt(1, magazine.getMagazineRecord());
        ResultSet resultSet = query.executeQuery();

        return getSubscriptions(list, resultSet);
    }

    private List<Subscription> getSubscriptions(List<Subscription> list, ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            list.add(new Subscription(resultSet.getInt("registro_suscripcion"), resultSet.getString("nombre_suscriptor"), resultSet.getInt("registro_revista"), resultSet.getString("nombre_revista"), resultSet.getBigDecimal("total_pago"),
                    PaymentEnum.value(resultSet.getString("intervalo_pago")), String.valueOf(resultSet.getDate("fecha_registro")), String.valueOf(resultSet.getDate("fecha_fin")), SubscriptionStatus.value(resultSet.getString("estado_suscripcion")), SubscriptionLike.value(resultSet.getString("like_suscripcion"))));
        }

        return list;
    }

    @Override
    public List<Subscription> listWhereSubscriberName(Profile profile) throws SQLException {
        List<Subscription> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_EDITOR_SUBSCRIPTIONS);
        query.setString(1, profile.getEditorName());
        ResultSet resultSet = query.executeQuery();

        return getSubscriptions(list, resultSet);
    }

    @Override
    public Subscription getSubscription(Subscription subscription) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_SUBSCRIPTION);
        query.setInt(1, subscription.getMagazineRecord());
        query.setString(2, subscription.getSubscriberName());
        ResultSet resultSet = query.executeQuery();

        if(resultSet.next()){
            return new Subscription(resultSet.getInt("registro_suscripcion"), resultSet.getString("nombre_suscriptor"), resultSet.getInt("registro_revista"), resultSet.getString("nombre_revista"), resultSet.getBigDecimal("total_pago"),
                    PaymentEnum.value(resultSet.getString("intervalo_pago")), String.valueOf(resultSet.getDate("fecha_registro")), String.valueOf(resultSet.getDate("fecha_fin")), SubscriptionStatus.value(resultSet.getString("estado_suscripcion")), SubscriptionLike.value(resultSet.getString("like_suscripcion")));
        }

        return null;
    }

    @Override
    public Subscription getSubscriptionLikes(int magazineRecord) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_SUBSCRIPTION_LIKES);
        query.setInt(1, magazineRecord);
        ResultSet resultSet = query.executeQuery();

        if(resultSet.next()){
            Subscription subscription = new Subscription();
            subscription.setLikes(resultSet.getInt("likes"));
            return subscription;
        }
        return null;
    }

    @Override
    public int getSubscriptionNumberLikes(int magazineRecord) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_SUBSCRIPTION_LIKES);
        query.setInt(1, magazineRecord);
        ResultSet resultSet = query.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("likes");
        }
        return 0;
    }

    @Override
    public int getSubscriptionNumberLikesBetween(int magazineRecord, LocalDate start, LocalDate end) throws SQLException {
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_SUBSCRIPTION_LIKES_BETWEEN);
        query.setInt(1, magazineRecord);
        query.setString(2, start.toString());
        query.setString(3, end.toString());
        ResultSet resultSet = query.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("likes");
        }
        return 0;
    }

    @Override
    public List<Subscription> getListSubscriptionsBetween(int magazineRecord, LocalDate start, LocalDate end) throws SQLException {
        List<Subscription> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_SUBSCRIPTIONS_BETWEEN);
        query.setInt(1, magazineRecord);
        query.setString(2, start.toString());
        query.setString(3, end.toString());
        ResultSet resultSet = query.executeQuery();

        return getSubscriptions(list, resultSet);
    }

    @Override
    public List<Subscription> getListSubscriptionsLikesBetween(int magazineRecord, LocalDate start, LocalDate end) throws SQLException {
        List<Subscription> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_SUBSCRIPTION_WHERE_LIKES_BETWEEN);
        query.setInt(1, magazineRecord);
        query.setString(2, start.toString());
        query.setString(3, end.toString());
        ResultSet resultSet = query.executeQuery();

        return getSubscriptions(list, resultSet);
    }

    @Override
    public List<Subscription> getListSubscriptions(int magazineRecord) throws SQLException {
        List<Subscription> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_SUBSCRIPTIONS);
        query.setInt(1, magazineRecord);
        ResultSet resultSet = query.executeQuery();

        return getSubscriptions(list, resultSet);
    }

    @Override
    public List<Subscription> listWhereSubscriptionLike(int magazineRecord) throws SQLException {
        List<Subscription> list = new ArrayList<>();
        PreparedStatement query = Connector.getConnection().prepareStatement(GET_SUBSCRIPTION_WHERE_LIKE);
        query.setInt(1, magazineRecord);
        ResultSet resultSet = query.executeQuery();

        return getSubscriptions(list, resultSet);
    }
}
