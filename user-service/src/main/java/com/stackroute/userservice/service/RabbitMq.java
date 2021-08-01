package com.stackroute.userservice.service;

import com.stackroute.userservice.model.Activities;
import com.stackroute.userservice.model.SocketTaskDoneNotification;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.model.UserProfile;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMq {

    private RabbitTemplate rabbitTemplate;
    @Autowired
    public RabbitMq(RabbitTemplate rabbitTemplate){this.rabbitTemplate=rabbitTemplate;}
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;
    @Value("${spring.rabbitmq.exchange2}")
    private String exchange2;
    @Value("${spring.rabbitmq.routingkey2}")
    private String routingkey2;

    public void send(User user){
        rabbitTemplate.convertAndSend(exchange,routingkey,user);
        rabbitTemplate.convertAndSend(exchange2,routingkey2,user);
        System.out.println("heloo"+user);
    }
    @Value("${spring.rabbitmq.exchangeSocket1}")
    private String exchangeSocket;
    @Value("${spring.rabbitmq.routingkeySocket1}")
    private String routingKeySocket;
    public void sender(SocketTaskDoneNotification socketTaskDoneNotification){
        rabbitTemplate.convertAndSend(exchangeSocket, routingKeySocket, socketTaskDoneNotification);
        System.out.println("My Response to Socket Notification Service is "+socketTaskDoneNotification);
    }
}
