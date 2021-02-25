package com.nubiform.idus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class IdusApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdusApplication.class, args);
	}

}
