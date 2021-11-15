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
import java.sql.PreparedStatement;
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
     * @return true if the connect is successful
     */
    public boolean connectToDB() {
        boolean connection = false;

        try {
            this.conn = DriverManager.getConnection(this.url, ConnectionData.DB_USER, ConnectionData.DB_PASSWORD);
            connection = conn.isValid(50000);
            this.getConn().setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("Error al conectar con Postgres: " + ex);
        }
        return connection;
    }

    /**
     *
     * @return true if the connection close successful
     */
    public boolean closeConnectionDB() {
        boolean connection = false;
        try {
            this.conn.close();
            connection = this.conn.isClosed();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexion: " + ex);
        }
        return connection;
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

    public Persona getPersona(int ci) throws SQLException {
        Persona p = null;
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT ci, nombre, apellido, fecha_nac, telefono, "
                + "nombre_usuario, \"contraseña\", saldo_ucucoins, departamento, email, email_confirmado FROM public.personas WHERE ci ='%s'", ci));
        while (rs.next()) {
            p = new Persona(rs.getInt("ci"), rs.getString("nombre"), rs.getString("apellido"), Date.valueOf(rs.getString("fecha_nac")),
                    rs.getInt("telefono"), rs.getString("departamento"), rs.getString("email"), rs.getString("nombre_usuario"),
                    rs.getString("contraseña"), rs.getInt("saldo_ucucoins"), rs.getBoolean(("email_confirmado")));
        }

        return p;
    }

    public Persona getPersona(String userName) throws SQLException {
        Persona p = null;
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT ci, nombre, apellido, fecha_nac, telefono, "
                + "nombre_usuario, \"contraseña\", saldo_ucucoins, departamento, email, email_confirmado FROM public.personas WHERE nombre_usuario ='%s'", userName));
        while (rs.next()) {
            p = new Persona(rs.getInt("ci"), rs.getString("nombre"), rs.getString("apellido"), Date.valueOf(rs.getString("fecha_nac")),
                    rs.getInt("telefono"), rs.getString("departamento"), rs.getString("email"), rs.getString("nombre_usuario"),
                    rs.getString("contraseña"), rs.getInt("saldo_ucucoins"), rs.getBoolean(("email_confirmado")));
        }

        return p;
    }

    public int getTotalOfferedUcuCoins(Persona persona) throws SQLException {
        int result = 0;
        Statement stmt = this.getConn().createStatement();
        String query = String.format("SELECT ofer.ucucoins_ofrecidas FROM Ofertas ofer, Publicaciones pub WHERE ofer.id_publicacion = pub.id_publicacion AND ofer.aceptada is null "
                + "AND pub.ci_publicante <> %s AND (ofer.ci_ofertante = %s OR %s in (SELECT ci_ofertante from Ofertas Of2 WHERE Of2.id_oferta = ofer.id_oferta_padre))", persona.getCi(), persona.getCi(), persona.getCi());
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            result += rs.getInt(1);
        }

        return result;
    }

    public String[] getDepartments() throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT nombre_departamento FROM public.departamentos");

        while (rs.next()) {
            result.add(rs.getString("nombre_departamento"));
        }

        return result.toArray(new String[0]);
    }

    public String[] getCategories() throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT nombre_categoria FROM public.categorias");

        while (rs.next()) {
            result.add(rs.getString("nombre_categoria"));
        }

        return result.toArray(new String[0]);
    }

    public List<Publicacion> getPublicaciones(Persona persona) throws SQLException {
        List<Publicacion> publicaciones = new ArrayList<>();
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT id_publicacion,ci_publicante, cantidad, id_categoria, nombre_producto, descripcion_producto, "
                + "valor_ucu_coin_estimado, vendida, Imagen FROM public.publicaciones where publicaciones.ci_publicante = '%s' AND publicaciones.vendida = 'false'", persona.getCi()));
        while (rs.next()) {
            Publicacion p = new Publicacion(Integer.valueOf(rs.getString("id_publicacion")), Integer.valueOf(rs.getString("id_categoria")), rs.getString("nombre_producto"),
                    rs.getString("descripcion_producto"), Integer.valueOf(rs.getString("valor_ucu_coin_estimado")), Integer.valueOf(rs.getString("cantidad")),
                    Boolean.valueOf(rs.getString("vendida")), rs.getString("Imagen"), Integer.valueOf(rs.getString("ci_publicante")));

            publicaciones.add(p);
        }

        return publicaciones;
    }

    public List<Publicacion> getPublicaciones(PublicationFilter filter) throws SQLException {
        List<Publicacion> publicaciones = new ArrayList<>();

        String whereClause = String.format("vendida = false AND ci_publicante <> %s ", filter.getOwner().getCi());

        if (filter.getPattern() != null) {
            whereClause += String.format("AND nombre_producto like '%s' ", filter.getPattern());
        }

        if (filter.getCategory() != null) {
            whereClause += String.format("AND id_categoria = %s ", filter.getCategory());
        }

        if (filter.getMinValue() != null) {
            whereClause += String.format("AND valor_ucu_coin_estimado >= %s ", filter.getMinValue());
        }

        if (filter.getMaxValue() != null) {
            whereClause += String.format("AND valor_ucu_coin_estimado <= %s ", filter.getMaxValue());
        }

        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT id_publicacion, cantidad, id_categoria, nombre_producto,ci_publicante, descripcion_producto, valor_ucu_coin_estimado, vendida, Imagen FROM public.publicaciones where %s", whereClause));
        while (rs.next()) {
            Publicacion p = new Publicacion(Integer.valueOf(rs.getString("id_publicacion")), Integer.valueOf(rs.getString("id_categoria")), rs.getString("nombre_producto"),
                    rs.getString("descripcion_producto"), Integer.valueOf(rs.getString("valor_ucu_coin_estimado")), Integer.valueOf(rs.getString("cantidad")),
                    Boolean.valueOf(rs.getString("vendida")), rs.getString("Imagen"), Integer.valueOf(rs.getString("ci_publicante")));

            publicaciones.add(p);
        }

        return publicaciones;
    }

    public Publicacion getPublicacion(int id) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT id_publicacion, cantidad, id_categoria, nombre_producto, descripcion_producto, "
                + "valor_ucu_coin_estimado, vendida, Imagen, ci_publicante FROM public.publicaciones where publicaciones.id_publicacion = %s", id));
        if (rs.next()) {
            Publicacion p = new Publicacion(Integer.valueOf(rs.getString("id_publicacion")), Integer.valueOf(rs.getString("id_categoria")), rs.getString("nombre_producto"),
                    rs.getString("descripcion_producto"), Integer.valueOf(rs.getString("valor_ucu_coin_estimado")), Integer.valueOf(rs.getString("cantidad")),
                    Boolean.valueOf(rs.getString("vendida")), rs.getString("Imagen"), Integer.valueOf(rs.getString("ci_publicante")));

            return p;
        }

        return null;
    }

    public void insertPublicacion(Publicacion p) {
        String xByte = p.getImagen();
        System.out.println(xByte);
    }

    public void updatePublicacion(Publicacion p) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        String update = String.format("UPDATE public.Publicaciones SET nombre_producto = '%s', descripcion_producto = '%s', "
                + "valor_ucu_coin_estimado = %s, cantidad = %s, id_categoria = %s, Imagen='%s' WHERE id_publicacion = %s",
                p.getNombreProducto(), p.getDescripcion(), p.getValorEstimado(), p.getCantidad(), p.getCategoria(), p.getImagen(), p.getId());

        stmt.executeUpdate(update);
        this.getConn().commit();
    }

    public void deletePublicacion(int id) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT id_oferta FROM Ofertas WHERE id_publicacion = %s ORDER BY id_oferta DESC", id));

        // ELIMINO TODAS LAS OFERTAS RELACIONADAS
        while (rs.next()) {
            int idOferta = rs.getInt(1);
            Statement stmt2 = this.getConn().createStatement();
            stmt2.executeUpdate(String.format("DELETE FROM publicacion_ofertas WHERE id_oferta = %s", idOferta));
            stmt2.executeUpdate(String.format("DELETE FROM Ofertas WHERE id_oferta = %s", idOferta));
        }

        // ELIMINO TODOS LOS MENSAJES ASOCIADOS A ESA PUBLICACION
        stmt.executeUpdate(String.format("DELETE FROM Mensajes WHERE id_publicacion = %s", id));

        // ELIMINO LA PUBLICACION
        stmt.executeUpdate(String.format("DELETE FROM Publicaciones WHERE id_publicacion = %s", id));

        this.getConn().commit();
    }

    public Oferta getOferta(int id) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        String query = String.format("SELECT id_oferta, id_publicacion, id_oferta_padre, ci_ofertante, aceptada, ucucoins_ofrecidas FROM Ofertas "
                + "WHERE id_oferta = %s", id);

        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return new Oferta(rs.getInt("id_oferta"), rs.getInt("id_publicacion"), rs.getBoolean("aceptada"),
                    rs.getInt("id_oferta_padre"), rs.getInt("ci_ofertante"), rs.getInt("ucucoins_ofrecidas"));
        }

        return null;
    }

    public List<Oferta> getOfertasEnviadas(Persona persona) throws SQLException {
        List<Oferta> ofertas = new ArrayList<>();
        Statement stmt = this.getConn().createStatement();
        String query = String.format("SELECT id_oferta, ofer.id_publicacion, id_oferta_padre, ci_ofertante, aceptada, ucucoins_ofrecidas, nombre_producto FROM Ofertas ofer, Publicaciones pub "
                + "WHERE ofer.id_publicacion = pub.id_publicacion AND ci_ofertante = %s AND aceptada is null", persona.getCi());
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int idOfertaPadre = rs.getString("id_oferta_padre") == null ? 0 : rs.getInt("id_oferta_padre");
            Oferta of = new Oferta(rs.getInt("id_oferta"), rs.getInt("id_publicacion"), rs.getBoolean("aceptada"),
                    idOfertaPadre, rs.getInt("ci_ofertante"), rs.getInt("ucucoins_ofrecidas"),
                    rs.getString("nombre_producto"));
            ofertas.add(of);
        }

        return ofertas;
    }

    public List<Oferta> getOfertasRecibidas(Persona persona) throws SQLException {
        List<Oferta> ofertas = new ArrayList<>();
        Statement stmt = this.getConn().createStatement();
        String query = String.format("SELECT id_oferta, Of1.id_publicacion, id_oferta_padre, ci_ofertante, aceptada, ucucoins_ofrecidas, nombre_producto FROM Ofertas Of1, Publicaciones Pub "
                + "WHERE Of1.ci_ofertante <> %s AND Of1.aceptada is null AND Of1.id_publicacion = Pub.id_publicacion AND "
                + "(Pub.ci_publicante = %s OR %s in (SELECT ci_ofertante from Ofertas Of2 WHERE Of2.id_oferta = Of1.id_oferta_padre))",
                persona.getCi(), persona.getCi(), persona.getCi());
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int idOfertaPadre = rs.getString("id_oferta_padre") == null ? 0 : rs.getInt("id_oferta_padre");
            Oferta of = new Oferta(rs.getInt("id_oferta"), rs.getInt("id_publicacion"), rs.getBoolean("aceptada"),
                    idOfertaPadre, rs.getInt("ci_ofertante"), rs.getInt("ucucoins_ofrecidas"),
                    rs.getString("nombre_producto"));
            ofertas.add(of);
        }

        return ofertas;
    }

    public void insertarOferta(Oferta oferta) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        String insertOferta = String.format("INSERT INTO Ofertas (id_publicacion, id_oferta_padre, ci_ofertante, aceptada, ucucoins_ofrecidas) "
                + "VALUES (%s, %s, %s, %s, %s)", oferta.getIdPublicacion(), oferta.getIdOfertaPadre() == 0 ? "NULL" : String.valueOf(oferta.getIdOfertaPadre()),
                oferta.getCIofertante(), oferta.isAceptada(), oferta.getUcucoinsOfrecidas());
        stmt.executeUpdate(insertOferta);

        String queryOferta = "SELECT max(id_oferta) from Ofertas";
        ResultSet rs = stmt.executeQuery(queryOferta);
        if (rs.next()) {
            oferta.setIdOferta(rs.getInt(1));
        }

        if (oferta.getIdOfertaPadre() != 0) {
            String querySetFalseAceptada = String.format("UPDATE Ofertas SET aceptada = false WHERE id_oferta = %s", oferta.getIdOfertaPadre());
            stmt.executeUpdate(querySetFalseAceptada);
        }

        PreparedStatement preparedStmt = this.getConn().prepareStatement("INSERT INTO publicacion_ofertas (id_publicacion, id_oferta) VALUES (?,?)");
        for (int i = 0; i < oferta.getPublicaciones().size(); i++) {
            preparedStmt.setInt(1, oferta.getPublicaciones().get(i).getId());
            preparedStmt.setInt(2, oferta.getIdOferta());
            preparedStmt.executeUpdate();
        }

        this.getConn().commit();
    }

    public void addNewPublicacion(Publicacion p) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        java.sql.Timestamp sqlDate = java.sql.Timestamp.valueOf(p.getFechaHora());
        String insert = String.format("INSERT INTO publicaciones(cantidad, fecha_hora_publicacion, nombre_producto, "
                + "descripcion_producto, valor_ucu_coin_estimado, id_categoria, vendida, imagen, ci_publicante) "
                + " VALUES (%s, '%s', '%s', '%s', %s, %s, '%s', '%s', '%s');", p.getCantidad(), sqlDate, p.getNombreProducto(), p.getDescripcion(),
                String.valueOf(p.getValorEstimado()), String.valueOf(p.getCategoria()), "false", p.getImagen(), p.getPublicante());

        stmt.executeUpdate(insert);
        this.getConn().commit();
    }

    public void aceptarOferta(Oferta oferta) throws SQLException {
        // Marco la oferta como aceptada
        Statement stmt = this.getConn().createStatement();
        String updateOffer = String.format("UPDATE Ofertas SET aceptada = true WHERE id_oferta = %s", oferta.getIdOferta());
        stmt.executeUpdate(updateOffer);

        String queryPub = String.format("SELECT id_publicacion from publicacion_ofertas WHERE id_oferta = %s", oferta.getIdOferta());
        ResultSet rs = stmt.executeQuery(queryPub);

        while (rs.next()) {
            // Marco las publicaciones involucradas como vendidas
            Statement stmt2 = this.getConn().createStatement();
            String updatePub = String.format("UPDATE Publicaciones SET vendida = true WHERE id_publicacion = %s", rs.getInt(1));
            stmt2.executeUpdate(updatePub);

            // Elimino esa publicacion de los items de las ofertas
            String deleteFromOtherOffers = String.format("DELETE FROM publicacion_ofertas WHERE id_publicacion = %s", rs.getInt(1));
            stmt2.executeUpdate(deleteFromOtherOffers);

            // Marco como rechazadas todas las ofertas realizadas a esa publicacion
            String markAsRejected = String.format("UPDATE Ofertas SET aceptada = false WHERE id_publicacion = %s", rs.getInt(1));
            stmt2.executeUpdate(markAsRejected);
        }

        String updatePub = String.format("UPDATE Publicaciones SET vendida = true WHERE id_publicacion = %s", oferta.getIdPublicacion());
        stmt.executeUpdate(updatePub);

        this.getConn().commit();
    }

    public void rechazarOferta(Oferta oferta) throws SQLException {
        // Marco la oferta como rechazada
        Statement stmt = this.getConn().createStatement();
        String updateOffer = String.format("UPDATE Ofertas SET aceptada = false WHERE id_oferta = %s", oferta.getIdOferta());
        stmt.executeUpdate(updateOffer);

        this.getConn().commit();
    }

    public boolean isPublicationFromThisPerson(Oferta oferta, Persona persona) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        String query = String.format("SELECT ci_publicante FROM Publicaciones WHERE id_publicacion = %s", oferta.getIdPublicacion());
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            return persona.getCi() == rs.getInt(1);
        }

        return false;
    }

    public void updateUcuCoinsWallet(Persona persona, int i) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        String updateWallet = String.format("UPDATE Personas SET saldo_ucucoins = %s WHERE ci = %s", i, persona.getCi());
        stmt.executeUpdate(updateWallet);

        this.getConn().commit();
    }

    public List<Publicacion> getPublicaciones(Oferta oferta) throws SQLException {
        List<Publicacion> publicaciones = new ArrayList<>();
        Statement stmt = this.getConn().createStatement();
        String queryPub = String.format("SELECT id_publicacion from publicacion_ofertas WHERE id_oferta = %s", oferta.getIdOferta());
        ResultSet rs = stmt.executeQuery(queryPub);

        while (rs.next()) {
            Publicacion p = this.getPublicacion(rs.getInt(1));
            publicaciones.add(p);
        }

        return publicaciones;
    }

    public void updateUser(Persona p) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        String update = String.format("UPDATE Personas SET nombre = '%s', apellido = '%s', "
                + "fecha_nac = %s, telefono = %s, departamento = %s, email= '%s', nombre_usuario = '%s', contraseña = '%s' WHERE ci = '%s'",
                p.getNombre(), p.getApellido(), FormatterService.formatData(p.getFechaDeNacimiento()),
                p.getTelefono(), p.getDepartamento(), p.getEmail(), p.getNombreDeUsuario(), p.getContraseña(), String.valueOf(p.getCi()));

        stmt.executeUpdate(update);
        this.getConn().commit();
    }

    public void insertNewMessage(Mensaje mensaje) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        java.sql.Timestamp sqlDate = java.sql.Timestamp.valueOf(mensaje.getFechaHora());
        String insert = String.format("INSERT INTO mensajes(contenido_mensaje, id_publicacion, id_mensaje_padre,"
                + " ci_origen, ci_destino, respondido, fecha_hora_mensaje) "
                + " VALUES ('%s', %s, %s, %s, %s, '%s', '%s');", mensaje.getContenido(), mensaje.getIdPublicacion(), "null", mensaje.getCIorigen(),
                mensaje.getCIdestino(), "false", sqlDate);
        stmt.executeUpdate(insert);
        this.getConn().commit();
    }

    public String getDepartmentName(String id) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        String query = String.format("SELECT nombre_departamento FROM Departamentos WHERE id_departamento = %s", id);
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            return rs.getString(1);
        }

        return "";
    }


    public String[] getUserNamesMensajesRecibidos(String[] ci_mensajes_destino) throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        Statement stmt = this.getConn().createStatement();
        for (String ci : ci_mensajes_destino) {
            ResultSet rs = stmt.executeQuery(String.format("SELECT nombre_usuario FROM personas WHERE ci = %s", ci));
            while (rs.next()) {
                result.add(rs.getString("nombre_usuario"));
            }
        }
        return result.toArray(new String[0]);
    }

    public String[] getCiMensajesRecibidos(Persona p) throws SQLException {
        ArrayList<String> ci_mensajes_destino = new ArrayList<>();
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM mensajes WHERE ci_destino = %s", p.getCi()));

        while (rs.next()) {
            if (!ci_mensajes_destino.contains(rs.getString("ci_origen"))) {
                ci_mensajes_destino.add(rs.getString("ci_origen"));
            }
        }

        return ci_mensajes_destino.toArray(new String[0]);
    }

    public List<Mensaje> getMensajes(Persona from, Persona to) throws SQLException {
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        Statement stmt = this.getConn().createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM mensajes WHERE (ci_origen = %s AND ci_destino = %s) OR (ci_origen = %s AND ci_destino = %s) ORDER BY fecha_hora_mensaje asc", to.getCi(), from.getCi(), from.getCi(), to.getCi()));

        while (rs.next()) {

            Mensaje m = new Mensaje(rs.getInt("id_mensaje"), rs.getString("contenido_mensaje"),
                    rs.getInt("id_mensaje_padre"), rs.getInt("id_publicacion"), rs.getInt("ci_origen"),
                    rs.getInt("ci_destino"), rs.getBoolean("respondido"), rs.getTimestamp("fecha_hora_mensaje").toLocalDateTime());
            
            mensajes.add(m);

        }
        return mensajes;
    }

    public void deleteOferta(Oferta oferta) throws SQLException {
        Statement stmt = this.getConn().createStatement();
        
        // Elimino los items de la oferta
        String deleteOffer = String.format("DELETE FROM publicacion_ofertas WHERE id_oferta = %s", oferta.getIdOferta());
        stmt.executeUpdate(deleteOffer);
        
        // Elimino la oferta
        deleteOffer = String.format("DELETE FROM Ofertas WHERE id_oferta = %s", oferta.getIdOferta());
        stmt.executeUpdate(deleteOffer);
        
        this.getConn().commit();
    }

}
