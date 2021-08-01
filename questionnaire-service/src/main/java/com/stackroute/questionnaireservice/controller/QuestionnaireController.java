package com.stackroute.questionnaireservice.controller;

import com.stackroute.questionnaireservice.exception.*;
import com.stackroute.questionnaireservice.model.Question;
import com.stackroute.questionnaireservice.model.ScoreBelt;
import com.stackroute.questionnaireservice.model.ScoreBeltsWithID;
import com.stackroute.questionnaireservice.service.QuestionnaireService;
import com.stackroute.questionnaireservice.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class QuestionnaireController {
//    @Autowired
//    private RabbitMQSender rabbitMQSender;

    private QuestionnaireService questionnaireService;
    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService){
        this.questionnaireService = questionnaireService;
    }
    @GetMapping("questions")
    public ResponseEntity<?> getQuestions() {
        try {
            return new ResponseEntity<List<Question>>(questionnaireService.getAllQuestions(), HttpStatus.OK);
        } catch (QuestionsNotFoundException e) {
            return new ResponseEntity<String>("No Questions are available.", HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<String>("Some problem happen we will get back soon.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("answers")
    public ResponseEntity<?> getAnswers(@RequestBody HashMap<Integer, String> selectedAnsweredMap, @RequestParam String email, @RequestParam String plan) {
        List<ScoreBelt> scoreBelts=null;
        try {
            System.out.println(email);
            System.out.println(plan);
        scoreBelts = questionnaireService.getScoreBelts(selectedAnsweredMap, email, plan);
            return new ResponseEntity<List<ScoreBelt>>(scoreBelts, HttpStatus.OK);
        } catch (AsweredOptionsMapNotExistsException e) {
            return new ResponseEntity<String>("Users' selected answers are not getting.", HttpStatus.NOT_FOUND);
        }  catch (NegativePercentageValueException e) {
            return new ResponseEntity<String>("Negative value of Percentage is not allowed.", HttpStatus.CONFLICT);
        }
        catch (ScoreBeltsNotExistsException e) {
            return new ResponseEntity<String>("Not get Score Belts.", HttpStatus.NOT_FOUND);
        }
        catch (UserEmailIsNotFoundException e) {
            return new ResponseEntity<String>("User Email is not found.", HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<String>("Some problem happen we will get back soon.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
