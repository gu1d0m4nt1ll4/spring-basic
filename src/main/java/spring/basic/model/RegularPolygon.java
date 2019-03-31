/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.basic.model;

/**
 *
 * @author guido.mantilla
 */
public class RegularPolygon {
    
    private Double base;
    private Double altura;
    private Double[] lado;

    /**
     * @return the base
     */
    public Double getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(Double base) {
        this.base = base;
    }

    /**
     * @return the altura
     */
    public Double getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(Double altura) {
        this.altura = altura;
    }

    /**
     * @return the lado
     */
    public Double[] getLado() {
        return lado;
    }

    /**
     * @param lado the lado to set
     */
    public void setLado(Double[] lado) {
        this.lado = lado;
    }
}
