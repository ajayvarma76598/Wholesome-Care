package com.stackroute.nlpservice.controller;

import com.stackroute.nlpservice.exception.NoActivityMatchWithDatSetException;
import com.stackroute.nlpservice.exception.NoTimeGivenException;
import com.stackroute.nlpservice.exception.NoTimeUnitGivenException;
import com.stackroute.nlpservice.model.Activity;
import com.stackroute.nlpservice.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
//creating controller class for getting calorie burnt from user's given activity detail in string as RequestParam
@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class ActivityController {
    private ActivityService activityService;
    @Autowired
    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }
    //method that call "getCalorieBurnt" method of "ActivityService"
    // and return Response status ok (response code 200) and List<Activity> when it is success
    //handle userDefined exceptions and return a valid error message as a String
    // and handle 500 bad request, return a string as error message
    @RequestMapping("calorieBurnt")
    public ResponseEntity<?> getCalorieBurnt(@RequestParam String activityDetail){
        try {
            return new ResponseEntity<List<Activity>>(activityService.getCalorieBurnt(activityDetail), HttpStatus.OK);
        } catch (NoTimeGivenException e){
            return new ResponseEntity<String>("Please give activity time.", HttpStatus.CONFLICT);
        } catch (NoTimeUnitGivenException e){
            return new ResponseEntity<String>("Please give proper activity time unit.", HttpStatus.CONFLICT);
        } catch (NoActivityMatchWithDatSetException e){
            return new ResponseEntity<String>("Sorry! your activity is not known by our data set.", HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<String>("Some problem happen we will get back soon.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
