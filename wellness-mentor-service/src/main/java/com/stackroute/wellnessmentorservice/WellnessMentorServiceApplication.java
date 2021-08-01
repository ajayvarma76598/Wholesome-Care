package com.stackroute.wellnessmentorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WellnessMentorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WellnessMentorServiceApplication.class, args);
	}

}
