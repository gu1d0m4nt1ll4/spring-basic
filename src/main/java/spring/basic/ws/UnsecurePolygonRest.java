/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.basic.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spring.basic.model.RegularPolygon;
import spring.basic.service.PolygonService;

/**
 *
 * @author guido.mantilla
 */
@RestController
@RequestMapping("/unsecure-polygon")
public class UnsecurePolygonRest {

    @Autowired
    private PolygonService polygonService;

    @PostMapping("/calcular-area")
    @ResponseBody
    public double calcularArea(@RequestBody RegularPolygon regularPolygon) {
        return polygonService.calcularArea(regularPolygon.getBase(), regularPolygon.getAltura());
    }

    @PostMapping("/calcular-perimetro")
    @ResponseBody
    public double calcularPerimetro(@RequestBody RegularPolygon regularPolygon) {
        return polygonService.calcularPerimetro(regularPolygon.getLado());
    }
}
