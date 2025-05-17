package com.eucl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EuclApplication {

	public static void main(String[] args) {
		SpringApplication.run(EuclApplication.class, args);
	}



}
