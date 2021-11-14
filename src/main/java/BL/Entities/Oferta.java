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
    int idOferta;
    int idPublicacion;
    Boolean aceptada;
    int idOfertaPadre;
    int CIofertante;
    int ucucoinsOfrecidas;
    String nombreProducto;

    List<Publicacion> publicaciones;
    
    public Oferta(int idPublicacion, Boolean aceptada, int idOfertaPadre, int CIofertante, int ucucoinsOfrecidas, List<Publicacion> publicaciones) {
        this.idPublicacion = idPublicacion;
        this.aceptada = aceptada;
        this.idOfertaPadre = idOfertaPadre;
        this.CIofertante = CIofertante;
        this.ucucoinsOfrecidas = ucucoinsOfrecidas;
        this.publicaciones = publicaciones;
    }
    
    public Oferta(int idOferta, int idPublicacion, Boolean aceptada, int idOfertaPadre, int CIofertante, int ucucoinsOfrecidas) {
        this.idPublicacion = idPublicacion;
        this.idOferta = idOferta;
        this.aceptada = aceptada;
        this.idOfertaPadre = idOfertaPadre;
        this.CIofertante = CIofertante;
        this.ucucoinsOfrecidas = ucucoinsOfrecidas;
    }
    
    public Oferta(int idOferta, int idPublicacion, Boolean aceptada, int idOfertaPadre, int CIofertante, int ucucoinsOfrecidas, String nombreProducto) {
        this.idPublicacion = idPublicacion;
        this.idOferta = idOferta;
        this.aceptada = aceptada;
        this.idOfertaPadre = idOfertaPadre;
        this.CIofertante = CIofertante;
        this.ucucoinsOfrecidas = ucucoinsOfrecidas;
        this.nombreProducto = nombreProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public Boolean isAceptada() {
        return aceptada;
    }

    public int getIdOfertaPadre() {
        return idOfertaPadre;
    }

    public int getCIofertante() {
        return CIofertante;
    }
    
    public int getUcucoinsOfrecidas() {
        return ucucoinsOfrecidas;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }
    
    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }
}
