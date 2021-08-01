package com.stackroute.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class CustomizedPlan {
    @Id
    private String email;
    private String name;
    private Date date;
    private String age;
    private String place;
    private String imageUrl;
    private String planType;
    private String physicalLevel;
    private String mentalLevel;
    private String dietLevel;
    private double physicalScore;
    private double mentalScore;
    private double dietScore;
    List<Users> followers;
    List<Users> following;
    List<Users> suggestions;
    List<Mentor> mentorList;
    private Plan plan;
}
