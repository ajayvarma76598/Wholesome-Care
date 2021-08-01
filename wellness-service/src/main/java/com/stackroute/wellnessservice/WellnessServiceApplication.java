package com.stackroute.wellnessservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WellnessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WellnessServiceApplication.class, args);
	}

}
