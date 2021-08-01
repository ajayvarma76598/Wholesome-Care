package com.stackroute.graphqueryservice.Service;

import com.stackroute.graphqueryservice.Model.ResponseNode;

import com.stackroute.graphqueryservice.Model.SocketNotification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    private RabbitTemplate rabbitTemplate;
    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;
//    @Value("${spring.rabbitmq.routingkey1}")
//    private String routingKey1;
//    public void send(List<ScoreBelt> scoreBelts){
//        rabbitTemplate.convertAndSend(exchange, routingKey, scoreBelts);
//        System.out.println(scoreBelts);
//    }

    @Value("${spring.rabbitmq.exchangeSocket}")
    private String exchangeSocket;
    @Value("${spring.rabbitmq.routingkeySocket}")
    private String routingKeySocket;
    public void send(ResponseNode responseNode){
        rabbitTemplate.convertAndSend(exchange, routingKey, responseNode);
        System.out.println("My Response to UserService is "+responseNode);
    }

    public void sender(SocketNotification socketNotification){
        rabbitTemplate.convertAndSend(exchangeSocket, routingKeySocket, socketNotification);
        System.out.println("My Response to Socket Notification Service is "+socketNotification);
    }

}

