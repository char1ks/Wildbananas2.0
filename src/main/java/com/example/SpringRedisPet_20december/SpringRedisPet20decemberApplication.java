package com.example.SpringRedisPet_20december;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringRedisPet20decemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisPet20decemberApplication.class, args);
	}

}
