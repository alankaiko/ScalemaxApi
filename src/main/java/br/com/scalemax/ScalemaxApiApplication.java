package br.com.scalemax;

import br.com.scalemax.config.property.ScalemaxApiProp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ScalemaxApiProp.class)
public class ScalemaxApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScalemaxApiApplication.class, args);
		System.out.println("APLICAÇÃO FUNCIONANDO...");
	}

}
