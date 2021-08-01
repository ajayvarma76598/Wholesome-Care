package com.stackroute.graphcommandservice.Controller;

import com.stackroute.graphcommandservice.Repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class RelationshipController {

    RelationshipRepository relationshipRepository;


    @Autowired
    public RelationshipController(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;

    }

    @GetMapping("/addRelation")
    public ResponseEntity<?> addRelationshipBetweenNodes(){
        relationshipRepository.dietWeightLossRelationship();
        return new ResponseEntity<>("Relationship Created", HttpStatus.OK);
    }



}
