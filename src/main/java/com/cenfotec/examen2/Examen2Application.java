package com.cenfotec.examen2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@EnableJpaRepositories("com.cenfotec.examen2.repository") 
@EntityScan("com.cenfotec.examen2.domain")
@SpringBootApplication
public class Examen2Application {

	public static void main(String[] args) {
		SpringApplication.run(Examen2Application.class, args);
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}

}
