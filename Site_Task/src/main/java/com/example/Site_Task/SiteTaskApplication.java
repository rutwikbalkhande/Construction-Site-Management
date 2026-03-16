package com.example.Site_Task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SiteTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteTaskApplication.class, args);
		System.out.println("Site Task Service started.........");
	}

}
