/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

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
    
    private static final String columnas = "IdPublicacion, FechaHoraPublicacion, IdCategoria, NombreProducto, Descripcion, ValorEstimado, Cantidad, CIpublicante";

    public Publicacion(int id, LocalDateTime fechaHora, int categoria, String nombreProducto, String descripcion, int valorEstimado, int cantidad, int publicante) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.categoria = categoria;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.valorEstimado = valorEstimado;
        this.cantidad = cantidad;
        this.publicante = publicante;
    }
    
    public String insertarPublicacion() {
        String valores = String.format("&s,&s,&s,&s,&s,&s,&s,&s", getIdQuery(), getFechaHoraQuery(), getCategoriaQuery(), getNombreProductoQuery(), 
                getDescripcionQuery(), getValorEstimadoQuery(), getCantidadQuery(), getPublicanteQuery());
        
        return String.format("INSERT INTO Publicaciones(&s) VALUES(&s)", columnas, valores);
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public int getCategoria() {
        return categoria;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getValorEstimado() {
        return valorEstimado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getPublicante() {
        return publicante;
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
}
