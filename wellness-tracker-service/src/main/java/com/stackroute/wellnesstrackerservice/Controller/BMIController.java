package com.stackroute.wellnesstrackerservice.Controller;

import com.stackroute.wellnesstrackerservice.Model.BMI;
import com.stackroute.wellnesstrackerservice.Service.BMICalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class BMIController {
    private BMICalculatorServiceImpl bmiCalculatorService;

    @Autowired
    public BMIController(BMICalculatorServiceImpl bmiCalculatorService) {
        this.bmiCalculatorService = bmiCalculatorService;
    }

    @PostMapping("/bmi")
    public ResponseEntity<?> getBmiIndex(@RequestBody BMI bmi){
        return new ResponseEntity<>(bmiCalculatorService.calculate(bmi), HttpStatus.OK);
    }
}
