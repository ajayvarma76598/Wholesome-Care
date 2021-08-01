package com.stackroute.wellnesstrackerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WellnessTrackerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WellnessTrackerServiceApplication.class, args);
	}

}
