package com.example.Service_Discovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class ServiceDiscoveryApplication {

	public static void main(String[] args) {

	//	log.info("eureka server running...");
		SpringApplication.run(ServiceDiscoveryApplication.class, args);
	//	log.info("eureka server started...");
		System.out.println("Service Discovery Started / Eureka server");
	}

}
