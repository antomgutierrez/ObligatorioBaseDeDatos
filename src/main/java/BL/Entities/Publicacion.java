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
public class Publicacion {

    private int id;
    private LocalDateTime fechaHora;
    private int categoria;
    private String nombreProducto;
    private String descripcion;
    private int valorEstimado;
    private int cantidad;
    private int publicante;
    private Boolean vendida;
    private String imagen;
    
    public Publicacion (int id) {
        this.id = id;
    }

    public Publicacion(int id, LocalDateTime fechaHora, int categoria, String nombreProducto, String descripcion, int valorEstimado, int cantidad, int publicante, Boolean vendida, String imagen) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.categoria = categoria;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.valorEstimado = valorEstimado;
        this.cantidad = cantidad;
        this.publicante = publicante;
        this.vendida = vendida;
        this.imagen = imagen;
    }
    
    public Publicacion(int id, int categoria, String nombreProducto, String descripcion, int valorEstimado, int cantidad, Boolean vendida, String imagen) {
        this.id = id;
        this.categoria = categoria;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.valorEstimado = valorEstimado;
        this.cantidad = cantidad;
        this.vendida = vendida;
        this.imagen = imagen;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(int valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPublicante() {
        return publicante;
    }

    public void setPublicante(int publicante) {
        this.publicante = publicante;
    }

    public Boolean isVendida() {
        return vendida;
    }

    public void setVendida(Boolean vendida) {
        this.vendida = vendida;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
