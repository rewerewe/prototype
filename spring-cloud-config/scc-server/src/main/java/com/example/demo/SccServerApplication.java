package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer // Config Server 설정
@SpringBootApplication
public class SccServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SccServerApplication.class, args);
	}

}
