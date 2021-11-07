/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Entities;

import BL.Helpers.FormatterService;
import java.time.LocalDateTime;

/**
 *
 * @author Administrador
 */
public class Publicacion {

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

    public boolean isVendida() {
        return vendida;
    }

    public void setVendida(boolean vendida) {
        this.vendida = vendida;
    }
    private int id;
    private LocalDateTime fechaHora;
    private int categoria;
    private String nombreProducto;
    private String descripcion;
    private int valorEstimado;
    private int cantidad;
    private int publicante;
    private boolean vendida;

    /*
    * Esto hay que cambiarlo, las columnas ya no se llaman asi.
     */
    private static final String columnas = "IdPublicacion, FechaHoraPublicacion, IdCategoria, NombreProducto, Descripcion, ValorEstimado, Cantidad, CIpublicante";

    public Publicacion(int id, LocalDateTime fechaHora, int categoria, String nombreProducto, String descripcion, int valorEstimado, int cantidad, int publicante, boolean vendida) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.categoria = categoria;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.valorEstimado = valorEstimado;
        this.cantidad = cantidad;
        this.publicante = publicante;
        this.vendida = vendida;
    }

    public Publicacion(int id, int categoria, String nombreProducto, String descripcion, int valorEstimado, int cantidad) {
        this.id = id;
        this.categoria = categoria;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.valorEstimado = valorEstimado;
        this.cantidad = cantidad;
    }

    public String insertarPublicacion() {
        String valores = String.format("&s,&s,&s,&s,&s,&s,&s,&s", getIdQuery(), getFechaHoraQuery(), getCategoriaQuery(), getNombreProductoQuery(),
                getDescripcionQuery(), getValorEstimadoQuery(), getCantidadQuery(), getPublicanteQuery());

        return String.format("INSERT INTO Publicaciones(&s) VALUES(&s)", columnas, valores);
    }

    public String insertarPublicacionOferta(String oferta) {
        return String.format("(&s,&s)", getIdQuery(), oferta);
    }

    private String getIdQuery() {
        return FormatterService.formatData(id);
    }

    private String getFechaHoraQuery() {
        return FormatterService.formatData(fechaHora);
    }

    private String getCategoriaQuery() {
        return FormatterService.formatData(categoria);
    }

    private String getNombreProductoQuery() {
        return FormatterService.formatData(nombreProducto);
    }

    private String getDescripcionQuery() {
        return FormatterService.formatData(descripcion);
    }

    private String getValorEstimadoQuery() {
        return FormatterService.formatData(valorEstimado);
    }

    private String getCantidadQuery() {
        return FormatterService.formatData(cantidad);
    }

    private String getPublicanteQuery() {
        return FormatterService.formatData(publicante);
    }

    private String getVendidaQuery() {
        return FormatterService.formatData(vendida);
    }
}
