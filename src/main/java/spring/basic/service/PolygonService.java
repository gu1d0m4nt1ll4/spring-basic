/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.basic.service;

/**
 *
 * @author guido.mantilla
 */
public interface PolygonService {

    public Double calcularArea(Double base, Double altura);

    public Double calcularPerimetro(Double... lado);
}
