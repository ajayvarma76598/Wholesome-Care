package com.stackroute.nlpservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class AppConfig {
    //creating bean for restTemplate
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
