package com.stackroute.wholesomecarewebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WholesomecareWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WholesomecareWebappApplication.class, args);
	}

}
