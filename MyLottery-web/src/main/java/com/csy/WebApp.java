package com.csy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("applicaction.properties")
public class WebApp {
	
	public static void main(String[] args) {
		SpringApplication.run(WebApp.class, args);
	}
}
