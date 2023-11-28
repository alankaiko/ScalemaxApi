package br.com.scalemax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScalemaxApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScalemaxApiApplication.class, args);
        System.out.println("APLICAÇÃO FUNCIONANDO...");
    }

}
