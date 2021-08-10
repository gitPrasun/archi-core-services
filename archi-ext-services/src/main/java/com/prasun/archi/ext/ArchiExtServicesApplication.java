package com.prasun.archi.ext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArchiExtServicesApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ArchiExtServicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ArchiExtServicesApplication.class, args);
	}

}
