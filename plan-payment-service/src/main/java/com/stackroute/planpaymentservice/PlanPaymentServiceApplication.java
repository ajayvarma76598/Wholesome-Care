package com.stackroute.planpaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PlanPaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanPaymentServiceApplication.class, args);
    }

}
