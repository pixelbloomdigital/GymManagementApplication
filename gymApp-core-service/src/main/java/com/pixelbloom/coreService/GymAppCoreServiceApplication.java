package com.pixelbloom.coreService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GymAppCoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymAppCoreServiceApplication.class, args);
	}

}
