package com.stackroute.wellnessmentorservice.controller;

import com.stackroute.wellnessmentorservice.exception.MentorAlreadyExistException;
import com.stackroute.wellnessmentorservice.exception.MentorNotExistException;
import com.stackroute.wellnessmentorservice.model.Mentor;
import com.stackroute.wellnessmentorservice.model.MentorResponse;
import com.stackroute.wellnessmentorservice.service.MentorService;
import com.stackroute.wellnessmentorservice.service.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mentor")
@CrossOrigin
public class MentorController {

    private MentorService mentorService;
    private RabbitMqSender rabbitMqSender;

    @Autowired
    public MentorController(MentorService mentorService,RabbitMqSender rabbitMqSender) {
        this.mentorService = mentorService;
        this.rabbitMqSender = rabbitMqSender;
    }

//    postmapping add mentor to database using mentorService get status response back
    @PostMapping("")
    public ResponseEntity<?> addMentor(@RequestBody Mentor mentor)throws MentorAlreadyExistException{
        if(mentorService.saveMentor(mentor)){
            MentorResponse response = new MentorResponse(mentor.getEmailId(),mentor.getName(),mentor.getExpertize());
            return new ResponseEntity<>(mentor, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("not created",HttpStatus.CONFLICT);
        }

    }

    @PutMapping("")
    public ResponseEntity<?> updateMentor(@RequestBody Mentor mentor)throws MentorNotExistException{
        if(mentorService.updateMentor(mentor)){
            return new ResponseEntity<>(mentor,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("not updated",HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMentorById(@PathVariable("id") String email) throws MentorNotExistException{
        return new ResponseEntity<>(mentorService.getMentorByEmail(email),HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllMentors(){
        return new ResponseEntity<>(mentorService.getAllMentors(),HttpStatus.OK);
    }



    @ExceptionHandler({MentorNotExistException.class})
    public ResponseEntity<?> mentorNotExistException(){
        return new ResponseEntity<>("Mentor not exist",HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({MentorAlreadyExistException.class})
    public ResponseEntity<?> mentorAlreadyExistException(){
        return new ResponseEntity<>("Mentor already exist",HttpStatus.CONFLICT);
    }
}
