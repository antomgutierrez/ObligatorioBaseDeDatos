/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

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
    
    private static final String columnas = "IdPublicacion, IdOferta, Aceptada, IdOfertaPadre, CIOfertante";
    
    public Oferta(int idPublicacion, int idOferta, boolean aceptada, int idOfertaPadre, int CIofertante) {
        this.idPublicacion = idPublicacion;
        this.idOferta = idOferta;
        this.aceptada = aceptada;
        this.idOfertaPadre = idOfertaPadre;
        this.CIofertante = CIofertante;
    }
    
    public String insertarOferta() {
        String valores = String.format("&s,&s,&s,&s,&s", getIdPublicacionQuery(), getIdOfertaQuery(), 
                isAceptadaQuery(), getIdOfertaPadreQuery(), getCIofertanteQuery());
        
        return String.format("INSERT INTO Ofertas(&s) VALUES(&s)", columnas, valores);
    }
    
    public String insertarPublicacionOferta() {
        String result = "";
        if (!publicaciones.isEmpty()) {
            result = publicaciones.stream().map(pub -> pub.insertarPublicacionOferta(getIdOfertaQuery()) + ",").reduce(result, String::concat);
            return String.format("INSERT INTO PublicacionOferta(IdPublicacion, IdOferta) VALUES &s", result.substring(0, result.length()- 1));
        }
        return result;
    }
    
    private String getIdPublicacionQuery() {
        return FormatterService.formatData(idPublicacion);
    }
    
    private String getIdOfertaQuery() {
        return FormatterService.formatData(idOferta);
    }
    
    private String isAceptadaQuery() {
        return FormatterService.formatData(aceptada);
    }
    
    private String getIdOfertaPadreQuery() {
        return FormatterService.formatData(idOfertaPadre);
    }
    
    private String getCIofertanteQuery() {
        return FormatterService.formatData(CIofertante);
    }
}
