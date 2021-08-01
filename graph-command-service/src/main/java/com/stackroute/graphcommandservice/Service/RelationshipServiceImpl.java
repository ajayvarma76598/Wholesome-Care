package com.stackroute.graphcommandservice.Service;

import com.stackroute.graphcommandservice.Repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationshipServiceImpl implements RelationshipService{
    private RelationshipRepository relationshipRepository;

    @Autowired
    public RelationshipServiceImpl(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }

    @Override
    public void userRelationship(String email) {
        relationshipRepository.userToPhysicalLevelRelationship(email);
        relationshipRepository.userToMentalLevelRelationship(email);
        relationshipRepository.userToDietLevelRelationship(email);
    }

    @Override
    public void mentorUserRelationship(String email) {
        relationshipRepository.addMentorToUserNode(email);
    }
}
