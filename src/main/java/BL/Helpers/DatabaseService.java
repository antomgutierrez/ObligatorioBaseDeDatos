/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Helpers;
import BL.Entities.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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
            this.getConn().setAutoCommit(false);
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
        return (pass.equals(user_pass));
    }

    public boolean validateCI(String ci) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT ci FROM public.personas WHERE ci =%s", ci));
        return !rs.next();
    }

    public boolean validateEmail(String email) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT email FROM public.personas WHERE email ='%s'", email));
        return !rs.next();
    }

    public boolean validateUserName(String username) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT nombre_usuario FROM public.personas WHERE nombre_usuario ='%s'", username));
        return !rs.next();
    }

    public void addNewUser(Persona p) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        stmt.executeUpdate(String.format("INSERT INTO public.personas"
                + "(ci, nombre, apellido, fecha_nac, telefono, nombre_usuario, \"contraseña\", saldo_ucucoins, departamento, email, email_confirmado)"
                + " VALUES ('%s', '%s', '%s', %s, '%s', '%s', '%s', 0, '%s', '%s', '%s');",
                String.valueOf(p.getCi()), p.getNombre(), p.getApellido(), FormatterService.formatData(p.getFechaDeNacimiento()), p.getTelefono(), p.getNombreDeUsuario(), String.valueOf(p.getContraseña()), p.getDepartamento(), p.getEmail(), String.valueOf(p.isEmailConfirmed())));
        this.getConn().commit();
    }

    public Persona getPersona(String userName) throws SQLException {
        Persona p = null;
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT ci, nombre, apellido, fecha_nac, telefono, "
                + "nombre_usuario, \"contraseña\", saldo_ucucoins, departamento, email, email_confirmado FROM public.personas WHERE nombre_usuario ='%s'", userName));
        while (rs.next()) {
            p = new Persona(Integer.valueOf(rs.getString("ci")), rs.getString("nombre"), rs.getString("apellido"), Date.valueOf(rs.getString("fecha_nac")),
                    Integer.valueOf(rs.getString("telefono")), rs.getString("departamento"), rs.getString("email"), rs.getString("nombre_usuario"),
                    rs.getString("contraseña"), Integer.valueOf(rs.getString("saldo_ucucoins")), Boolean.valueOf(rs.getString("email_confirmado")));
        }

        return p;
    }

    public String[] getDepartments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Publicacion> getPublicaciones(Persona persona) throws SQLException {
        List<Publicacion> publicaciones = new ArrayList<Publicacion>();
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT id_publicacion, cantidad, id_categoria, nombre_producto, descripcion_producto, valor_ucu_coin_estimado, vendida FROM public.publicaciones where publicaciones.ci_publicante ='%s'", persona.getCi()));
        while (rs.next()) {
            Publicacion p = new Publicacion(Integer.valueOf(rs.getString("id_publicacion")), Integer.valueOf(rs.getString("id_categoria")), rs.getString("nombre_producto"),
                    rs.getString("descripcion_producto"),Integer.valueOf(rs.getString("valor_ucu_coin_estimado")), Integer.valueOf(rs.getString("cantidad")), 
                    Boolean.valueOf(rs.getString("vendida")));
            publicaciones.add(p);
        }

        return publicaciones;
    }

}
