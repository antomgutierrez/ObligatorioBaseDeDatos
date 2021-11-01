/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Helpers;

import BL.Entities.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class DatabaseService {
    // aca ponemos todas las magias necesarias para conectar con la BD, insertar, bla bla bla
    /*
    private static final String msgColumns = "IdMensaje, ContenidoMensaje, IdMensajePadre, IdPublicacion, CIOrigen, CIDestino, Respondido";
    
    
    private static Connection con;
    
    private boolean testConnection() {
        try { 
            con = DriverManager.getConnection("jdbc:postgresql://192.168.56.2:5432/HolaBaseDeDatos", "postgres","admin123");
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public void startConnection() {
        if (testConnection()) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void insertar(Persona persona) {
    }
    
    public void insertar(Publicacion pub) {
    }
    
    public void insertar(Oferta oferta) {
        // si la oferta tiene un padre es necesario marcarlo como aceptado=false
    }
    
    public void insertar(Mensaje msg) {
        // si el padre esta vacio hay que ponerle el respondido=true
        String values = String.format("&s,&s,&s,&s,&s,&s,&s", msg.getIdQuery(), msg.getContenidoQuery(), msg.getIdPadreQuery(), msg.getIdPublicacionQuery(), msg.getCIorigenQuery(), 
                msg.getCIdestinoQuery(), msg.getRespondidoQuery());
        
        //Statement stmt = con.createStatement();
        //stmt.execute
        //return String.format("INSERT INTO Mensajes(&s) VALUES(&s)", msgColumns, values);
    }
    
    */
    
    
}
