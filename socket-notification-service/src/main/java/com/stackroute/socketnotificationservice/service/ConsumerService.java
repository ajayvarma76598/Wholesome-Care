package com.stackroute.socketnotificationservice.service;

import com.stackroute.socketnotificationservice.model.SocketNotification;
import com.stackroute.socketnotificationservice.model.SocketTaskDoneNotification;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@Data
public class ConsumerService implements RabbitListenerConfigurer {
    private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);
//    private SocketNotification socketNotification;
//    @RabbitListener(queues = "${spring.rabbitmq.queue}")
//    public void receivedMessage(SocketNotification socketNotification) throws InterruptedException {
//        logger.info("SocketNotification received is: " + socketNotification);
//        this.socketNotification = socketNotification;
//    }
    private SocketTaskDoneNotification socketTaskDoneNotification;
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessageFromUpdatedUserProfile(SocketTaskDoneNotification socketTaskDoneNotification) throws InterruptedException {
        logger.info("SocketNotification received is done task : " + socketTaskDoneNotification);
        System.out.println(socketTaskDoneNotification);
        this.socketTaskDoneNotification = socketTaskDoneNotification;
    }
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}

