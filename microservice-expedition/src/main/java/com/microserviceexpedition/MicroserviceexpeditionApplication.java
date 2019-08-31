package com.microserviceexpedition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceexpeditionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceexpeditionApplication.class, args);
	}

}
