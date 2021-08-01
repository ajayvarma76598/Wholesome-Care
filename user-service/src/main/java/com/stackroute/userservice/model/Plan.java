package com.stackroute.userservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Plan {
    List<Activities> activities;
    List<Activities> activitiesDone;
    private Diet_plan diet_plan;
    List<FoodItems> eatenItems;
}
