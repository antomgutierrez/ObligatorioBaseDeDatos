/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Entities;

import BL.Helpers.FormatterService;

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
    
    public Mensaje(int id, String contenido, int idPadre, int idPublicacion, int CIorigen, int CIdestino, boolean respondido) {
        this.id = id;
        this.contenido = contenido;
        this.idPadre = idPadre;
        this.idPublicacion = idPublicacion;
        this.CIorigen = CIorigen;
        this.CIdestino = CIdestino;
        this.respondido = respondido;
    }
    
    public String getIdQuery() {
        return FormatterService.formatData(id);
    }
    
    public String getContenidoQuery() {
        return FormatterService.formatData(contenido);
    }
    
    public String getIdPadreQuery() {
        return FormatterService.formatData(idPadre);
    }
    
    public String getIdPublicacionQuery() {
        return FormatterService.formatData(idPublicacion);
    }
    
    public String getCIorigenQuery() {
        return FormatterService.formatData(CIorigen);
    }
    
    public String getCIdestinoQuery() {
        return FormatterService.formatData(CIdestino);
    }
    
    public String getRespondidoQuery() {
        return FormatterService.formatData(respondido);
    }
}
