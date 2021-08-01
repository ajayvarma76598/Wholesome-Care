package com.stackroute.graphcommandservice.Service;

import com.stackroute.graphcommandservice.Exception.NodeAlreadyExistsException;
import com.stackroute.graphcommandservice.Exception.NodeNotFoundException;
import com.stackroute.graphcommandservice.Model.*;
import com.stackroute.graphcommandservice.Repository.MentorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService implements RabbitListenerConfigurer {
    private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    private UserNodeServiceImpl userNodeService;
    private RelationshipService relationshipService;
    private MentorRepository mentorRepository;

    @Autowired
    public ConsumerService(UserNodeServiceImpl userNodeService, RelationshipService relationshipService,MentorRepository mentorRepository) {
        this.userNodeService = userNodeService;
        this.relationshipService = relationshipService;
        this.mentorRepository=mentorRepository;
    }



    /*This method will save the Registered users  to Neo4j-DB*/
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(User user) throws NodeAlreadyExistsException {

        logger.info("User received is: " + user);
        UserNode userNode = new UserNode();
        userNode.setEmail(user.getEmail());
        userNode.setName(user.getName());
        userNode.setAge(user.getAge());
        userNode.setPlace(user.getPlace());
        if (user.getGender().equalsIgnoreCase("Female")){
            userNode.setImageUrl("https://cdn1.iconfinder.com/data/icons/website-internet/48/website_-_female_user-512.png");
        }
        else {
            userNode.setImageUrl("https://cdn1.iconfinder.com/data/icons/website-internet/48/website_-_male_user-512.png");
        }
        userNodeService.saveNewNode(userNode);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue2}")
    public void receivedMessageFromQuestionnaire(ScoreBeltsWithID scoreBeltsWithID) throws  NodeNotFoundException {
        String email = scoreBeltsWithID.getEmail();
        List<ScoreBelt> list = scoreBeltsWithID.getScoreBeltList();
        String planType = scoreBeltsWithID.getPlanType();
        if(planType.equalsIgnoreCase("PLATINUM")){
            relationshipService.mentorUserRelationship(email);
        }
        logger.info("ScoreBelt received is: " + list);
        String physicalColour =  list.stream().filter(a->a.getWellnessType().equalsIgnoreCase("Physical Wellness"))
                .findFirst().get().getBeltColour();
        Double physicalScore =  list.stream().filter(a->a.getWellnessType().equalsIgnoreCase("Physical Wellness"))
                .findFirst().get().getTotalPercentage();
        String mentalColour =  list.stream().filter(a->a.getWellnessType().equalsIgnoreCase("Mental Wellness"))
                .findFirst().get().getBeltColour();
        Double mentalScore = list.stream().filter(a->a.getWellnessType().equalsIgnoreCase("Mental Wellness"))
                .findFirst().get().getTotalPercentage();
        String dietColour =  list.stream().filter(a->a.getWellnessType().equalsIgnoreCase("Diet"))
                .findFirst().get().getBeltColour();
        Double dietScore =  list.stream().filter(a->a.getWellnessType().equalsIgnoreCase("Diet"))
                .findFirst().get().getTotalPercentage();

        userNodeService.updateExistingNode(email,planType, getPhysicalLevelId(physicalColour),getMentalLevelId(mentalColour),getDietLevelId(dietColour),physicalScore,mentalScore,dietScore);
        relationshipService.userRelationship(email);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue3}")
    public void receivedMentor(Mentor mentor) {

        logger.info("User received is: " + mentor);
        mentorRepository.save(mentor);

    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
    public int getPhysicalLevelId(String physical_level){
        switch (physical_level) {
            case "Red":
                return 144;
            case "Yellow":
                return 145;
            case "Blue":
                return 146;
            case "Orange":
                return 147;
            case "Green":
                return 143;
            default:
                return 144;
        }

    }

    public int getDietLevelId(String diet_level){
        switch (diet_level) {
            case "Green":
                return 153;
            case "Red":
                return 154;
            case "Yellow":
                return 155;
            case "Blue":
                return 156;
            case "Orange":
                return 157;
            default:
                return 154;
        }

    }

    public int getMentalLevelId(String mental_level){
        switch (mental_level) {
            case "Red":
                return 149;
            case "Yellow":
                return 150;
            case "Blue":
                return 151;
            case "Orange":
                return 152;
            case "Green":
                return 148;
            default:
                return 149;
        }

    }

}
