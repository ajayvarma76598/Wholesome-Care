package com.stackroute.graphqueryservice.Service;


import com.stackroute.graphqueryservice.Model.*;
import com.stackroute.graphqueryservice.Repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class QueryServiceImpl {

    private RelationshipRepository relationshipRepository;

    @Autowired
    public QueryServiceImpl(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }

    public ResponseNode getEmailBy(String email) {

        String name=relationshipRepository.getUserName(email);
        Date date = relationshipRepository.getUserDate(email);
        String physicalLevel = relationshipRepository.getUserphysicalLevel(email);
        String mentalLevel = relationshipRepository.getUserMentalLevel(email);
        String dietLevel = relationshipRepository.getUserDietLevel(email);
        double physicalScore = relationshipRepository.getUserPhysicalScore(email);
        double mentalScore = relationshipRepository.getUserMentalScore(email);
        double dietScore = relationshipRepository.getUserDietScore(email);

        String age= relationshipRepository.getUserAge(email);
        String place=relationshipRepository.getUserPlace(email);
        String imageUrl= relationshipRepository.getUserImageUrl(email);

        List<Activities> content1 = new ArrayList<>();
        int pcount=relationshipRepository.countOfGetAllActivitiesInTime(email);

        for (int i=0;i<pcount;i++){
            Activities activities1 = new Activities();
            activities1.setName(relationshipRepository.getAllActivitiesAsStringName(email).get(i));
            activities1.setActivityType(relationshipRepository.getAllActivitiesAsStringActivityType(email).get(i));
            activities1.setDuration_min(relationshipRepository.getAllActivitiesAsDuration(email).get(i));
            activities1.setDescription(relationshipRepository.getAllActivitiesAsStringDescription(email).get(i));
            activities1.setCalorieBurnt(relationshipRepository.getAllActivitiesAsStringCalorie(email).get(i));
            activities1.setAudioUrl(relationshipRepository.getAllActivitiesAsStringAudioUrl(email).get(i));
            activities1.setImageUrl(relationshipRepository.getAllActivitiesAsStringImageUrl(email).get(i));
            activities1.setVideoUrl(relationshipRepository.getAllActivitiesAsStringVideoUrl(email).get(i));
            activities1.setQuotationImageUrl(relationshipRepository.getAllActivitiesAsStringQuotationUrl(email).get(i));
            content1.add(activities1);
        }
        int mcount=relationshipRepository.countOfGetAllMentalActivitiesInTime(email);
        for (int i=0;i<mcount;i++){
            Activities activities1 = new Activities();
            activities1.setName(relationshipRepository.getAllMentalActivitiesAsStringName(email).get(i));
            activities1.setActivityType(relationshipRepository.getAllMentalActivitiesAsStringActivityType(email).get(i));
            activities1.setDuration_min(relationshipRepository.getAllMentalActivitiesAsDuration(email).get(i));
            activities1.setDescription(relationshipRepository.getAllMentalActivitiesAsStringDescription(email).get(i));
            activities1.setCalorieBurnt(relationshipRepository.getAllMentalActivitiesAsStringCalorie(email).get(i));
            activities1.setAudioUrl(relationshipRepository.getAllMentalActivitiesAsStringAudioUrl(email).get(i));
            activities1.setImageUrl(relationshipRepository.getAllMentalActivitiesAsStringImageUrl(email).get(i));
            activities1.setVideoUrl(relationshipRepository.getAllMentalActivitiesAsStringVideoUrl(email).get(i));
            activities1.setQuotationImageUrl(relationshipRepository.getAllMentalActivitiesAsStringQuotationUrl(email).get(i));
            content1.add(activities1);
        }

        List<FoodItems> breakfast = new ArrayList<>();
        int bcount = relationshipRepository.countTimeNodesForUser(email,"Breakfast");
        for (int i=0;i<bcount;i++){
            FoodItems items= new FoodItems();
            items.setName(relationshipRepository.getAllNodeNames(email,"Breakfast").get(i));
            items.setUnit(relationshipRepository.getAllNodeUnit(email,"Breakfast").get(i));
            items.setQuantity(relationshipRepository.getAllNodeQuantity(email,"Breakfast").get(i));
            items.setCalorie(relationshipRepository.getAllNodeCalorie(email,"Breakfast").get(i));
            items.setCarbs(relationshipRepository.getAllNodeCarbs(email,"Breakfast").get(i));
            items.setProtein(relationshipRepository.getAllNodeProtein(email,"Breakfast").get(i));
            items.setSugar(relationshipRepository.getAllNodeSugar(email,"Breakfast").get(i));
            items.setImageUrl(relationshipRepository.getAllNodeImageUrl(email,"Breakfast").get(i));
            breakfast.add(items);
        }
        List<FoodItems> lunch = new ArrayList<>();
        int lcount = relationshipRepository.countTimeNodesForUser(email,"Lunch");
        for (int i=0;i<lcount;i++){
            FoodItems items= new FoodItems();
            items.setName(relationshipRepository.getAllNodeNames(email,"Lunch").get(i));
            items.setUnit(relationshipRepository.getAllNodeUnit(email,"Lunch").get(i));
            items.setQuantity(relationshipRepository.getAllNodeQuantity(email,"Lunch").get(i));
            items.setCalorie(relationshipRepository.getAllNodeCalorie(email,"Lunch").get(i));
            items.setCarbs(relationshipRepository.getAllNodeCarbs(email,"Lunch").get(i));
            items.setProtein(relationshipRepository.getAllNodeProtein(email,"Lunch").get(i));
            items.setSugar(relationshipRepository.getAllNodeSugar(email,"Lunch").get(i));
            items.setImageUrl(relationshipRepository.getAllNodeImageUrl(email,"Lunch").get(i));
            lunch.add(items);
        }
        List<FoodItems> dinner = new ArrayList<>();
        int dcount = relationshipRepository.countTimeNodesForUser(email,"Dinner");
        for (int i=0;i<dcount;i++){
            FoodItems items= new FoodItems();
            items.setName(relationshipRepository.getAllNodeNames(email,"Dinner").get(i));
            items.setUnit(relationshipRepository.getAllNodeUnit(email,"Dinner").get(i));
            items.setQuantity(relationshipRepository.getAllNodeQuantity(email,"Dinner").get(i));
            items.setCalorie(relationshipRepository.getAllNodeCalorie(email,"Dinner").get(i));
            items.setCarbs(relationshipRepository.getAllNodeCarbs(email,"Dinner").get(i));
            items.setProtein(relationshipRepository.getAllNodeProtein(email,"Dinner").get(i));
            items.setSugar(relationshipRepository.getAllNodeSugar(email,"Dinner").get(i));
            items.setImageUrl(relationshipRepository.getAllNodeImageUrl(email,"Dinner").get(i));
            dinner.add(items);
        }
        List<FoodItems> morningSnacks = new ArrayList<>();
        int morning_snacksCount = relationshipRepository.countTimeNodesForUser(email,"Morning Snacks");
        for (int i=0;i<morning_snacksCount;i++){
            FoodItems items= new FoodItems();
            items.setName(relationshipRepository.getAllNodeNames(email,"Morning Snacks").get(i));
            items.setUnit(relationshipRepository.getAllNodeUnit(email,"Morning Snacks").get(i));
            items.setQuantity(relationshipRepository.getAllNodeQuantity(email,"Morning Snacks").get(i));
            items.setCalorie(relationshipRepository.getAllNodeCalorie(email,"Morning Snacks").get(i));
            items.setCarbs(relationshipRepository.getAllNodeCarbs(email,"Morning Snacks").get(i));
            items.setProtein(relationshipRepository.getAllNodeProtein(email,"Morning Snacks").get(i));
            items.setSugar(relationshipRepository.getAllNodeSugar(email,"Morning Snacks").get(i));
            items.setImageUrl(relationshipRepository.getAllNodeImageUrl(email,"Morning Snacks").get(i));
            morningSnacks.add(items);
        }
        List<FoodItems> eveningSnacks = new ArrayList<>();
        int ecount = relationshipRepository.countTimeNodesForUser(email,"Evening Snacks");
        for (int i=0;i<ecount;i++){
            FoodItems items= new FoodItems();
            items.setName(relationshipRepository.getAllNodeNames(email,"Evening Snacks").get(i));
            items.setUnit(relationshipRepository.getAllNodeUnit(email,"Evening Snacks").get(i));
            items.setQuantity(relationshipRepository.getAllNodeQuantity(email,"Evening Snacks").get(i));
            items.setCalorie(relationshipRepository.getAllNodeCalorie(email,"Evening Snacks").get(i));
            items.setCarbs(relationshipRepository.getAllNodeCarbs(email,"Evening Snacks").get(i));
            items.setProtein(relationshipRepository.getAllNodeProtein(email,"Evening Snacks").get(i));
            items.setSugar(relationshipRepository.getAllNodeSugar(email,"Evening Snacks").get(i));
            items.setImageUrl(relationshipRepository.getAllNodeImageUrl(email,"Evening Snacks").get(i));
            eveningSnacks.add(items);
        }
        Diet_plan diet_plan = new Diet_plan(breakfast,morningSnacks,lunch,eveningSnacks,dinner);
        List<Activities> done = new ArrayList<>();
        List<FoodItems> eatenItems = new ArrayList<>();
        Plan plan = new Plan(content1,done,diet_plan,eatenItems);
        List<Users> followers= getUserNodeListFrom(relationshipRepository.followers(email));
        List<Users> following= getUserNodeListFrom(relationshipRepository.following(email));
        List<Users> suggestions= getUserNodeListFrom(relationshipRepository.suggestions(email));
        String planType = relationshipRepository.getUserPlanType(email);
        List<Mentor> mentorList = new ArrayList<>();
        if (planType.equalsIgnoreCase("PLATINUM")){
            for (int i=0;i<3;i++){
                Mentor mentor = new Mentor();
                mentor.setEmailId(relationshipRepository.getMentorsEmail(email).get(i));
                mentor.setName(relationshipRepository.getMentorsName(email).get(i));
                mentor.setExpertize(relationshipRepository.getMentorsExpertize(email).get(i));
                mentorList.add(mentor);
            }
        }
        ResponseNode responseNode = new ResponseNode(email,name,date,age,place,imageUrl,planType,physicalLevel,mentalLevel,dietLevel,physicalScore,mentalScore,dietScore,followers,following,suggestions,mentorList,plan);
        return responseNode;
    }

    public List<Users> getUserNodeListFrom(List<String> email){
        List<Users> list=new ArrayList<>();
        int count = (int) email.stream().count();
        for (int i=0;i<count;i++){
            Users userNode = new Users();
            userNode.setName(relationshipRepository.getUserName(email.get(i)));
            userNode.setDate(relationshipRepository.getUserDate(email.get(i)));
            userNode.setEmail(email.get(i));
            userNode.setPhysicalLevel(relationshipRepository.getUserphysicalLevel(email.get(i)));
            userNode.setDietLevel(relationshipRepository.getUserDietLevel(email.get(i)));
            userNode.setMentalLevel(relationshipRepository.getUserMentalLevel(email.get(i)));
            userNode.setAge(relationshipRepository.getUserAge(email.get(i)));
            userNode.setPlace(relationshipRepository.getUserPlace(email.get(i)));
            userNode.setImageUrl(relationshipRepository.getUserImageUrl(email.get(i)));
            list.add(userNode);
        }
        return list;
    }
}
