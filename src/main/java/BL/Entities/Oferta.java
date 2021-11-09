/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Entities;

import java.util.List;

/**
 *
 * @author Administrador
 */
public class Oferta {
    int idPublicacion;
    int idOferta;
    boolean aceptada;
    int idOfertaPadre;
    int CIofertante;
    List<Publicacion> publicaciones;
    
    public Oferta(int idPublicacion, int idOferta, boolean aceptada, int idOfertaPadre, int CIofertante) {
        this.idPublicacion = idPublicacion;
        this.idOferta = idOferta;
        this.aceptada = aceptada;
        this.idOfertaPadre = idOfertaPadre;
        this.CIofertante = CIofertante;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public int getIdOfertaPadre() {
        return idOfertaPadre;
    }

    public int getCIofertante() {
        return CIofertante;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }
}
