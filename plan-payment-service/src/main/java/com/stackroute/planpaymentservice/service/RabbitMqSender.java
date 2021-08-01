package com.stackroute.planpaymentservice.service;


import com.stackroute.planpaymentservice.model.PaymentResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {

    @Value("${spring.rabbitmq.queue}")
    public String queue;

    @Value("${spring.rabbitmq.exchange}")
    public String exchange ;

    @Value("${spring.rabbitmq.routingKey}")
    public String routingKey ;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(PaymentResponse paymentResponse){
        rabbitTemplate.convertAndSend(exchange,routingKey,paymentResponse);
    }


}
