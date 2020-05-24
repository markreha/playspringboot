package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
public class PlayspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayspringbootApplication.class, args);
	}

}
