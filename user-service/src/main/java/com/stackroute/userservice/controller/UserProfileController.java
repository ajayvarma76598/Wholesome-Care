package com.stackroute.userservice.controller;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.Activities;
import com.stackroute.userservice.model.SocketTaskDoneNotification;
import com.stackroute.userservice.model.UserProfile;
import com.stackroute.userservice.service.RabbitMq;
import com.stackroute.userservice.service.UserProfileService;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserProfileController {
    RabbitMq rabbitMq;
    private UserProfileService userProfileService;
    @Autowired
    public UserProfileController(UserProfileService userProfileService, RabbitMq rabbitMq) {
        this.userProfileService = userProfileService;
        this.rabbitMq = rabbitMq;
    }
    @GetMapping("/userProfile")
    public ResponseEntity<?> getUserByEmail(@RequestParam("email") String email) {
        try {
            UserProfile userProfile = userProfileService.getUserByEmailId(email);
            System.out.println(userProfile);
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found",HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/find")
    public ResponseEntity<?> findUserByEmail(@RequestParam("email") String email) {

            return new ResponseEntity<>(userProfileService.checkUserIsPresent(email), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserByEmail(@RequestBody String user) {
        try{

            System.out.println("helli"+user);
            JSONObject json = new JSONObject(user);
            System.out.println("p-"+json.toString());
           System.out.println("e"+json.getString("email"));
            UserProfile user1 = userProfileService.updateUserByEmailId(json);
            System.out.println("updated"+user1);

            return new ResponseEntity<>(user1,HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>("User Not Found",HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateUserProfileByEmail(@RequestBody UserProfile userProfile) {
        List<Activities> activitiesDoneList = userProfile.getDays().stream().filter(d->(d.getLocalDate().compareTo(LocalDate.now())==0))
                .findFirst().get().getPlan().getActivitiesDone();
        SocketTaskDoneNotification socketTaskDoneNotification = new SocketTaskDoneNotification(userProfile.getName(), activitiesDoneList);
        this.rabbitMq.sender(socketTaskDoneNotification);
        try{
            userProfileService.updateDaysInUserProfile(userProfile);
            return new ResponseEntity<>(userProfile,HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>("User Not Found",HttpStatus.CONFLICT);
        }
    }

//    @PutMapping("/updateAppointment")
//    public ResponseEntity<?> updateUserProfileAppointmentsByEmail(@RequestBody List<Appointment> appointments, @RequestParam String email) {
//        try{
//            UserProfile userProfile = userProfileService.updateAppointmentInUserProfile(appointments,email);
//            return new ResponseEntity<>(userProfile,HttpStatus.OK);
//        } catch( Exception e) {
//            return new ResponseEntity<>("User Not Found",HttpStatus.CONFLICT);
//        }
//    }

    @PutMapping("/updateAppointment")
    public ResponseEntity<?> updateUserProfileAppointmentsByEmail(@RequestBody UserProfile userProfile) {
        try{
            UserProfile userProfile1 = userProfileService.updateAppointmentInUserProfile(userProfile);
            return new ResponseEntity<>(userProfile1,HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>("User Not Found",HttpStatus.CONFLICT);
        }
    }
}