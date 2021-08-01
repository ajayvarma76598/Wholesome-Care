package com.stackroute.wellnessmentorservice.service;

import com.stackroute.wellnessmentorservice.model.MentorAuthenResponse;
import com.stackroute.wellnessmentorservice.model.MentorResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {

    @Value("${spring.rabbitmq.queue}")
    public String queue;

    @Value("${spring.rabbitmq.exchange}")
    public String exchange;

    @Value("${spring.rabbitmq.routingKey}")
    public String routingKey;

    @Value("${spring.rabbitmq.queue1}")
    public String queue1;

    @Value("${spring.rabbitmq.exchange1}")
    public String exchange1;

    @Value("${spring.rabbitmq.routingKey1}")
    public String routingKey1;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void send(MentorResponse mentorResponse,MentorAuthenResponse mentorAuthenResponse){
        rabbitTemplate.convertAndSend(exchange,routingKey,mentorResponse);
        rabbitTemplate.convertAndSend(exchange1,routingKey1,mentorAuthenResponse);
    }

}
