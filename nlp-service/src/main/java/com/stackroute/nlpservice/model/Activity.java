package com.stackroute.nlpservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//creating a model class for getting response of activity detail for calculating calorie burnt
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private Double duration;
    private String timeDuration;
    private String activity;
    private Double calorieBurnt;
    public Activity(String activity, Double calorieBurnt){
        this.activity = activity;
        this.calorieBurnt = calorieBurnt;
    }
    public Activity(Double duration, String timeDuration, String activity){
        this.duration = duration;
        this.timeDuration = timeDuration;
        this.activity = activity;
    }
}
