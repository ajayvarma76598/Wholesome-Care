package com.stackroute.userservice.controller;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.MyTaskExecutor;
import com.stackroute.userservice.service.RabbitMq;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserController {

    private UserService userService;
    private RabbitMq rabbitMq;
    private MyTaskExecutor myTaskExecutor;

    @Autowired
    public UserController(UserService userService, RabbitMq rabbitMq, MyTaskExecutor myTaskExecutor) {
        this.userService = userService;
        this.rabbitMq = rabbitMq;
        this.myTaskExecutor=myTaskExecutor;
    }

    @PostMapping("/register")

    public ResponseEntity<?> registerUserProfile(@RequestBody User user) {
        try {
            User userResponse = userService.registerUser(user);
            rabbitMq.send(userResponse);
            return new ResponseEntity<>(userResponse,HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>("User Already Exist",HttpStatus.CONFLICT);

        }
    }


    @GetMapping("/register")
    public ResponseEntity<?> getUserByEmail(@RequestParam("email") String email) {

        try {
            User user = userService.getUserByEmailId(email);
            return new ResponseEntity<>(user,HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found",HttpStatus.CONFLICT);
        }


    }
    @PutMapping("/register")
    public ResponseEntity<?> updateUserByEmail(@RequestBody User user) {
        try{
            User user1 = userService.updateUserByEmailId(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        } catch(UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found",HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/test")
    public ResponseEntity<?> alterAgeByEmail(@RequestParam("email") String email) {

        try {
            User user = userService.getUserByEmailId(email);
            user.setAge("55");
            User user2= userService.updateUserByEmailId(user);

            return new ResponseEntity<>(user2,HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found",HttpStatus.CONFLICT);
        }


    }

    @GetMapping("/start")
    public ResponseEntity<?> startService(@RequestParam int hour,@RequestParam int min,@RequestParam int sec) {

        myTaskExecutor.startExecutionAt(hour, min, sec);
        return new ResponseEntity<>("Success",HttpStatus.OK);

    }

}
