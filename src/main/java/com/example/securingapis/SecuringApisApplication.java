package com.example.securingapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SecuringApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuringApisApplication.class, args);
	}

}
