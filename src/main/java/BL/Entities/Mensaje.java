/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Entities;

import java.time.LocalDateTime;

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
    private LocalDateTime fechaHora;

    public Mensaje(int id, String contenido, int idPadre, int idPublicacion, int CIorigen, int CIdestino, boolean respondido, LocalDateTime fechaHora) {
        this.id = id;
        this.contenido = contenido;
        this.idPadre = idPadre;
        this.idPublicacion = idPublicacion;
        this.CIorigen = CIorigen;
        this.CIdestino = CIdestino;
        this.respondido = respondido;
        this.fechaHora = fechaHora;
    }

    public Mensaje(String contenido, int idPublicacion, int CIorigen, int CIdestino, boolean respondido, LocalDateTime fechaHora) {
        this.contenido = contenido;
        this.idPublicacion = idPublicacion;
        this.CIorigen = CIorigen;
        this.CIdestino = CIdestino;
        this.respondido = respondido;
        this.fechaHora = fechaHora;
    }

    public int getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    public int getIdPadre() {
        return idPadre;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public int getCIorigen() {
        return CIorigen;
    }

    public int getCIdestino() {
        return CIdestino;
    }

    public boolean isRespondido() {
        return respondido;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public void setCIorigen(int CIorigen) {
        this.CIorigen = CIorigen;
    }

    public void setCIdestino(int CIdestino) {
        this.CIdestino = CIdestino;
    }

    public void setRespondido(boolean respondido) {
        this.respondido = respondido;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
