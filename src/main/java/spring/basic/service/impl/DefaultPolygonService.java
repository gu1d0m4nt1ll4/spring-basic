/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.basic.model.exception.ParamNotValidException;
import spring.basic.service.PolygonService;
import spring.basic.util.CalculatorUtil;

/**
 *
 * @author guido.mantilla
 */
@Service
public class DefaultPolygonService implements PolygonService {

    @Autowired
    private CalculatorUtil calculatorService;

    @Override
    public Double calcularArea(Double base, Double altura) {

        if (base == null) {
            throw new ParamNotValidException("La base no puede ser NULL");
        }
        if (base < 0) {
            throw new ParamNotValidException("La base debe ser mayor igual a cero (0)");
        }

        if (altura == null) {
            throw new ParamNotValidException("La altura no puede ser NULL");
        }
        if (altura < 0) {
            throw new ParamNotValidException("La altura debe ser mayor igual a cero (0)");
        }

        return calculatorService.calcularMultiplicacion(base, altura);
    }

    @Override
    public Double calcularPerimetro(Double... lado) {

        double perimetro = 0;
        for (double l : lado) {
            perimetro = calculatorService.calcularSuma(perimetro, l);
        }

        return perimetro;
    }
}
