package com.example.tracer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class TracerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TracerApplication.class, args);
	}
}
