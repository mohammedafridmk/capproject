package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //identifies this spring boot application to be the eureka server
public class EurekaServerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerServiceApplication.class, args);
	}

}
