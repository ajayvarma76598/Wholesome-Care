package com.stackroute.graphqueryservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Activities {


    private String name;
    private String activityType;
    private String duration_min;
    private String description;
    private String calorieBurnt;
    private String imageUrl;
    private String videoUrl;
    private String quotationImageUrl;
    private String audioUrl;

}
