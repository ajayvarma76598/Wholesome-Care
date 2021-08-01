package com.stackroute.sentimentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SentimentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentimentServiceApplication.class, args);
	}

}
