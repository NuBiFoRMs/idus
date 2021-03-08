package com.nubiform.idus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@EnableWebMvc
public class IdusApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdusApplication.class, args);
	}

	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
		return new InternalResourceViewResolver();
	}
}
