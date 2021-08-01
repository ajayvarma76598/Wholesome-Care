package com.stackroute.socketnotificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SocketNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocketNotificationServiceApplication.class, args);
	}

}
