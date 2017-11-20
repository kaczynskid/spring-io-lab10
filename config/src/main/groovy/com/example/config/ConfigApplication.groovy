package com.example.config

import groovy.transform.CompileStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
@CompileStatic
class ConfigApplication {

	static void main(String[] args) {
		SpringApplication.run ConfigApplication, args
	}
}
