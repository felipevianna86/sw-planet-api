package com.example.swplanetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SwPlanetApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SwPlanetApiApplication.class, args);
	}

}
