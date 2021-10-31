/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

/**
 *
 * @author Administrador
 */
public class Mensaje {
    private int id;
    private String contenido;
    private int idPadre;
    private int idPublicacion;
    private int CIorigen;
    private int CIdestino;
    private boolean respondido;
    
    private static final String columnas = "IdMensaje, ContenidoMensaje, IdMensajePadre, IdPublicacion, CIOrigen, CIDestino, Respondido";
    
    public Mensaje(int id, String contenido, int idPadre, int idPublicacion, int CIorigen, int CIdestino, boolean respondido) {
        this.id = id;
        this.contenido = contenido;
        this.idPadre = idPadre;
        this.idPublicacion = idPublicacion;
        this.CIorigen = CIorigen;
        this.CIdestino = CIdestino;
        this.respondido = respondido;
    }
    
    public String insertarMensaje() {
        String valores = String.format("&s,&s,&s,&s,&s,&s,&s", getIdQuery(), getContenidoQuery(), getIdPadreQuery(), getIdPublicacionQuery(), getCIorigenQuery(), 
                getCIdestinoQuery(), getRespondidoQuery());
        
        return String.format("INSERT INTO Mensajes(&s) VALUES(&s)", columnas, valores);
    }
    
    private String getIdQuery() {
        return FormatterService.formatData(id);
    }
    
    private String getContenidoQuery() {
        return FormatterService.formatData(contenido);
    }
    
    private String getIdPadreQuery() {
        return FormatterService.formatData(idPadre);
    }
    
    private String getIdPublicacionQuery() {
        return FormatterService.formatData(idPublicacion);
    }
    
    private String getCIorigenQuery() {
        return FormatterService.formatData(CIorigen);
    }
    
    private String getCIdestinoQuery() {
        return FormatterService.formatData(CIdestino);
    }
    
    private String getRespondidoQuery() {
        return FormatterService.formatData(respondido);
    }
}
