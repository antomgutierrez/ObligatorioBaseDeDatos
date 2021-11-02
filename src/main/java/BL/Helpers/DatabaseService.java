/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Administrador
 */
public class DatabaseService {

    /**
     *
     * @param host
     * @param port
     * @param database
     */
    public DatabaseService(String host, String port, String database) {
        this.url = String.format("jdbc:postgresql://%s:%s/%s", host, port, database);
    }

    /**
     *
     * @return
     */
    public Connection getConn() {
        return conn;
    }

    /**
     *
     * @param conn
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    private Connection conn = null;
    private String url = "";

    /**
     *
     * @param user the name for auth in the DB
     * @param pass the pass for auth in the DB
     * @return true if the connect is succesfull
     */
    public boolean connectToDB(String user, String pass) {
        boolean connection = false;
        
        try {
            this.conn = DriverManager.getConnection(this.url, user, pass);
            connection = conn.isValid(50000);
        } catch (SQLException ex) {
            System.out.println("Error al conectar con Postgres: " + ex);
        } finally {
            return connection;
        }
    }

    /**
     *
     * @return true if the connection close succesfull
     */
    public boolean closeConnectionDB() {
        boolean connection = false;
        try {
            this.conn.close();
            connection = this.conn.isClosed();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexion: " + ex);
        } finally {
            return connection;
        }
    }

}
