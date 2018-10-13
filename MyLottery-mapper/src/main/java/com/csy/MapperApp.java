package com.csy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@MapperScan("com.csy.mapper")
public class MapperApp {

	public static void main(String[] args) {
		SpringApplication.run(MapperApp.class, args);
	}
}
