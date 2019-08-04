package com.cenfotec.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@EnableJpaRepositories("com.cenfotec.pm.repository") 
@EntityScan("com.cenfotec.pm.domain")
@SpringBootApplication
public class PMApplication {

	public static void main(String[] args) {
		SpringApplication.run(PMApplication.class, args);
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}

}
