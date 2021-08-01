package com.stackroute.graphcommandservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Node
public class UserNode {

    @Id
    private String email;
    private String name;
    private Date date;
    private String age;
    private String place;
    private String imageUrl;
    private String planType;
    private int physical_level;
    private int mental_level;
    private int diet_level;
    private double physicalScore;
    private double mentalScore;
    private double dietScore;

}
