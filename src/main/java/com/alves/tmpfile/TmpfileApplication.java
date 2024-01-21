package com.alves.tmpfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class TmpfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmpfileApplication.class, args);
	}

}