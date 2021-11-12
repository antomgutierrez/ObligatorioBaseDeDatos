/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Entities;

/**
 *
 * @author Administrador
 */
public class PublicationFilter {
    
    private String pattern = null;
    private Integer category = null;
    private Integer minValue = null;
    private Integer maxValue = null;
    private final Persona owner;
    
    public PublicationFilter(Persona owner) {
        this.owner = owner;
    }

    public Persona getOwner() {
        return owner;
    }

    public String getPattern() {
        return pattern;
    }

    public Integer getCategory() {
        return category;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }
    
    
}
