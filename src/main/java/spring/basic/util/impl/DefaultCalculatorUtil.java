/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.basic.util.impl;

import org.springframework.stereotype.Component;
import spring.basic.util.CalculatorUtil;

/**
 *
 * @author guido.mantilla
 */
@Component
public class DefaultCalculatorUtil implements CalculatorUtil {

    @Override
    public double calcularSuma(double a, double b) {
        return a + b;
    }

    @Override
    public double calcularResta(double a, double b) {
        return a - b;
    }

    @Override
    public double calcularMultiplicacion(double a, double b) {
        return a * b;
    }

    @Override
    public double calcularDivision(double a, double b) {
        return a / b;
    }
}
