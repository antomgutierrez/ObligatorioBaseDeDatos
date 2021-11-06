/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Helpers;

import BL.Entities.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public boolean login(String login_user, String pass) throws SQLException {

        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT \"contraseña\" FROM public.personas WHERE nombre_usuario = '%s'", login_user));
        String user_pass = null;
        while (rs.next()) {
            user_pass = rs.getString("contraseña");
        }
        return (pass == null ? user_pass == null : pass.equals(user_pass));
    }

    public boolean validate_ci(String ci) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT ci FROM public.personas WHERE ci =%s", ci));
        return !rs.next();
    }

    public boolean validate_email(String email) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT email FROM public.personas WHERE email ='%s'", email));
        return !rs.next();
    }

    public boolean validate_username(String username) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT nombre_usuario FROM public.personas WHERE nombre_usuario ='%s'", username));
        return !rs.next();
    }
    
    public boolean add_new_user(String ci,String name, String lastName,
                String date,String phone, String username, String password, String department, String email) throws SQLException{
        //this.getConn().setAutoCommit(false);
        Statement stmt = this.getConn().createStatement();
        stmt.executeUpdate(String.format("INSERT INTO public.personas"
                + "(ci, nombre, apellido, fecha_nac, telefono, nombre_usuario, \"contraseña\", saldo_ucucoins, departamento, email)"
                + " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '0', '%s', '%s');"
                , ci, name, lastName, date, phone, username, password, department, email));

        return true;

    }

}
