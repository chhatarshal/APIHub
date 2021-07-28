package com.APIHub.cookidea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.APIHub.cookidea", " org.springframework.security.authentication", "org.modelmapper"})
public class CookIdeaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookIdeaApplication.class, args);
	}

}
