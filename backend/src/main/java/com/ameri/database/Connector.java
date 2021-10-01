package com.ameri.database;

import com.ameri.other.Constant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static Connection connection = null;

    /**
     * Constructor
     */
    public Connector(){
        conectar();
    }

    /**
     * método utilizado para establecer
     * la conexión a la base de datos
     */
    private void conectar() {

        if(connection == null){
            try {
                connection = DriverManager.getConnection(Constant.URL,Constant.USER,Constant.PASSWORD);
                System.out.println("Conexión realizada con éxito.");
            } catch (SQLException e) {
                System.err.println("Algo salió mal.");
            }

        } else {
            System.out.println("Conexión aún vigente.");
        }
    }
    public void desconectar(){

        connection = null;
        System.out.println("Desconectado a la base de Datos.");
    }

}
