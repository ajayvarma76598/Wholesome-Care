package com.stackroute.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodItems {


    private String name;
    private String quantity;
    private String unit;
    private String imageUrl;
    private String calorie;
    private String sugar;
    private String carbs;
    private String protein;
}
