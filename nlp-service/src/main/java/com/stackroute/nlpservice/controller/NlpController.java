package com.stackroute.nlpservice.controller;

import com.stackroute.nlpservice.model.Nutrient;
import com.stackroute.nlpservice.service.NlpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
//creating controller class for getting nutrition information from user's given food consumed details in string as RequestParam
@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class NlpController {
    private NlpService nlpService;
    @Autowired
    public NlpController(NlpService nlpService){
        this.nlpService = nlpService;
    }
    //method that call "getIngredientInformationOfFoodConsumed" method of "NlpService"
    // and return Response status ok (response code 200) and List<Nutrient> when it is success
    // and handle 500 bad request, return a string as error message
    @RequestMapping("nutrition")
    public ResponseEntity<?> getTotalNutrientsAndList(@RequestParam String foodConsumed){
        try {
            return new ResponseEntity<List<Nutrient>>(nlpService.getIngredientInformationOfFoodConsumed(foodConsumed), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>("Some problem happen we will get back soon.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
