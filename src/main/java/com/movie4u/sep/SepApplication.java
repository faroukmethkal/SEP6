package com.movie4u.sep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SepApplication {

	public static void main(String[] args) {
		SpringApplication.run(SepApplication.class, args);
	}

}
