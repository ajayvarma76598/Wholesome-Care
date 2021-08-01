package com.stackroute.socketnotificationservice.controller;

import com.stackroute.socketnotificationservice.model.Activities;
import com.stackroute.socketnotificationservice.model.SocketNotification;
import com.stackroute.socketnotificationservice.model.SocketTaskDoneNotification;
import com.stackroute.socketnotificationservice.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin
public class SocketNotificationController {
    private ConsumerService consumerService;
    @Autowired
    public SocketNotificationController(ConsumerService consumerService){
        this.consumerService=consumerService;
    }
//    @MessageMapping("/message")
//    @SendTo("/topic/messages")
//    public SocketNotification notification(String message){
//        System.out.println(message);
//        System.out.println(consumerService.getSocketNotification());
//        return consumerService.getSocketNotification();
//    }
    @MessageMapping("/doneTask")
    @SendTo("/topic/notifyWithDoneTask")
    public SocketTaskDoneNotification notificationDoneTask(String message){
        System.out.println(message);
        System.out.println(consumerService.getSocketTaskDoneNotification());
        return consumerService.getSocketTaskDoneNotification();
    }
}
