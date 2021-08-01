package com.stackroute.wellnessservice.controller;

import com.stackroute.wellnessservice.Repository.GuestUserRepository;
import com.stackroute.wellnessservice.Repository.HelplineRepository;
import com.stackroute.wellnessservice.exception.GuestUserNotCreatedException;
import com.stackroute.wellnessservice.exception.HelplineNotFoundException;
import com.stackroute.wellnessservice.model.EmailRequest;
import com.stackroute.wellnessservice.model.GuestUser;
import com.stackroute.wellnessservice.model.Helpline;
import com.stackroute.wellnessservice.service.GuestUserService;
import com.stackroute.wellnessservice.service.HelplineService;
import com.stackroute.wellnessservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class GuestUserController {
    private GuestUserService guestUserService;
    private HelplineRepository helplineRepository;
    private GuestUserRepository guestUserRepository;
    private HelplineService helplineService;
    private MailService mailService;
    GuestUser guestUser;

    @Autowired
    public GuestUserController(GuestUserService guestUserService,HelplineRepository helplineRepository,HelplineService helplineService,MailService mailService){
        this.guestUserService=guestUserService;
        this.helplineRepository=helplineRepository;
        this.helplineService=helplineService;
        this.mailService=mailService;
    }

    @PostMapping("/guestuser")
    public ResponseEntity<?> saveUser(@RequestBody GuestUser guestUser){
        try{
            GuestUser guestUserResponse=guestUserService.saveUser(guestUser);
            return new ResponseEntity<>(guestUserResponse, HttpStatus.OK);
        }catch (GuestUserNotCreatedException e) {
            return new ResponseEntity<>("guest user not entered details",HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/guestuser/{email}")
    public ResponseEntity<?> getEmail(@PathVariable("email") String email){
        try{
            GuestUser userResponse=guestUserRepository.getUserByEmail(email);
            return new ResponseEntity<>(userResponse,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("guest user email not found",HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/helpline")
    public ResponseEntity<?> saveHelpline(@RequestBody Helpline helpline){
        try{
            Helpline helplineResponse=helplineService.saveHelpline(helpline);
            return new ResponseEntity<>(helplineResponse, HttpStatus.OK);
        }catch (HelplineNotFoundException e) {
            return new ResponseEntity<>("helpline details not entered",HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/helpline/{cityName}")
    public ResponseEntity<?> getHelpline(@PathVariable("cityName") String cityName){
        try{
            Helpline helplineResponse=helplineRepository.getHelplineByCityName(cityName);
            return new ResponseEntity<>(helplineResponse,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("helpline by city name not found",HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/sendingemail")
    public ResponseEntity<?> send(@RequestBody EmailRequest emailRequest) throws Exception {
        boolean result = mailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage(),emailRequest.getCityName());
        if (result) {
            return ResponseEntity.ok("Email is sent successfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("email sending failed");
        }
    }
    /*@RequestMapping("/sendingemail")
    public String send() {
        guestUser.setEmail("wholesomecare2021@gmail.com.com");

        try {
            mailService.sendEmail(guestUser);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }*/

}
