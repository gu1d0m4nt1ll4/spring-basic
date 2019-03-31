/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.basic.service.PolygonService;

/**
 *
 * @author guido.mantilla
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private PolygonService polygonService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Double area = polygonService.calcularArea(10D, 3D);
        System.out.println("The area is: " + area);

    }
}
