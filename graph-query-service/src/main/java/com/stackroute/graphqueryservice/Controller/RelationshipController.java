package com.stackroute.graphqueryservice.Controller;


import com.stackroute.graphqueryservice.Model.ResponseNode;
import com.stackroute.graphqueryservice.Model.SocketNotification;
import com.stackroute.graphqueryservice.Model.Users;
import com.stackroute.graphqueryservice.Repository.RelationshipRepository;
import com.stackroute.graphqueryservice.Service.QueryServiceImpl;
import com.stackroute.graphqueryservice.Service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class RelationshipController {

    RelationshipRepository relationshipRepository;
    QueryServiceImpl queryService;
    RabbitMQSender rabbitMQSender;

    @Autowired
    public RelationshipController(RelationshipRepository relationshipRepository, QueryServiceImpl queryService, RabbitMQSender rabbitMQSender) {
        this.relationshipRepository = relationshipRepository;
        this.queryService= queryService;
        this.rabbitMQSender=rabbitMQSender;
    }

    @GetMapping("/message")
    public String test() {
        return "Hello JavaInUse Called in graph query Service";
    }

    @GetMapping("/email")
    public ResponseEntity<?> getResponse(@RequestParam String email){
        ResponseNode responseNode = queryService.getEmailBy(email);
        rabbitMQSender.send(responseNode);
        return new ResponseEntity<>(queryService.getEmailBy(email),HttpStatus.OK);
    }

    @PostMapping("/follow")
    public ResponseEntity<?> followUser(@RequestParam String fromEmail, @RequestParam String toEmail){
        relationshipRepository.createRelationshipBetweenUsers(fromEmail,toEmail);
        String fromName = relationshipRepository.getUserName(fromEmail);
        String toName = relationshipRepository.getUserName(toEmail);
        SocketNotification socketNotification = new SocketNotification(fromEmail,toEmail,fromName+ " just started following , "+toName);
        rabbitMQSender.sender(socketNotification);
        return new ResponseEntity<>("Relationship Created",HttpStatus.OK);
    }

    @PostMapping("/unfollow")
    public ResponseEntity<?> unfollowUser(@RequestParam String fromEmail, @RequestParam String toEmail){
        relationshipRepository.removeRelationshipBetweenUsers(fromEmail,toEmail);
        return new ResponseEntity<>("Relationship Ended",HttpStatus.OK);
    }

    @GetMapping("/followers")
    public ResponseEntity<?> getFollowers(@RequestParam String email){
        List<Users> followers= getUserNodeListFrom(relationshipRepository.followers(email));

        return new ResponseEntity<>(followers,HttpStatus.OK);
    }

    @GetMapping("/following")
    public ResponseEntity<?> getFollowing(@RequestParam String email){
        List<Users> following= getUserNodeListFrom(relationshipRepository.following(email));

        return new ResponseEntity<>(following,HttpStatus.OK);
    }

    @GetMapping("/suggestions")
    public ResponseEntity<?> getSuggestions(@RequestParam String email){
        List<Users> suggestions= getUserNodeListFrom(relationshipRepository.suggestions(email));
        return new ResponseEntity<>(suggestions,HttpStatus.OK);
    }

    public List<Users> getUserNodeListFrom(List<String> email){
        List<Users> list=new ArrayList<>();
        int count = (int) email.stream().count();
        for (int i=0;i<count;i++){
            Users userNode = new Users();
            userNode.setName(relationshipRepository.getUserName(email.get(i)));
            userNode.setDate(relationshipRepository.getUserDate(email.get(i)));
            userNode.setEmail(email.get(i));
            userNode.setPhysicalLevel(relationshipRepository.getUserphysicalLevel(email.get(i)));
            userNode.setDietLevel(relationshipRepository.getUserDietLevel(email.get(i)));
            userNode.setMentalLevel(relationshipRepository.getUserMentalLevel(email.get(i)));
            userNode.setAge(relationshipRepository.getUserAge(email.get(i)));
            userNode.setPlace(relationshipRepository.getUserPlace(email.get(i)));
            userNode.setImageUrl(relationshipRepository.getUserImageUrl(email.get(i)));
            list.add(userNode);
        }
        return list;
    }
}
