package com.ameri.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static Connection connection = null;
    private final String USER="usuario1";
    private final String URL="jdbc:mysql://localhost:3306/revista_app";
    private final String PASSWORD="ConTr@.34";

    /**
     * Constructor
     */
    public Connector(){
        connect();
    }

    /**
     * método utilizado para establecer
     * la conexión a la base de datos
     */
    private void connect() {

        if(connection == null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL,USER,PASSWORD);
                System.out.println("Conexión realizada con éxito.");
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println("Algo salió mal.");
            }

        } else {
            System.out.println("Conexión aún vigente.");
        }

        if(connection == null){
            connect();
        }
    }

    public static Connection getConnection(){return connection;}

    public void disconnect() throws SQLException{

        if(connection != null){
            if(!connection.isClosed()){
                connection = null;
                System.out.println("Desconectado a la base de Datos.");
            }
        }
    }

}
