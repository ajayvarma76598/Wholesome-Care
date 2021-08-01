package com.stackroute.nlpservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//creating a model class for getting response of nutrition detail for user's food consumed
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nutrient {
    @JsonProperty("name")
    private String foodName;
    @JsonProperty("serving_size_g")
    private double servingSizeGram;
    @JsonProperty("sugar_g")
    private double sugar;
    @JsonProperty("fiber_g")
    private double fiber;
    @JsonProperty("sodium_mg")
    private double sodium;
    @JsonProperty("potassium_mg")
    private double potassium;
    @JsonProperty("fat_saturated_g")
    private double fatSaturate;
    @JsonProperty("fat_total_g")
    private double fat;
    @JsonProperty("cholesterol_mg")
    private double cholesterol;
    @JsonProperty("protein_g")
    private double protein;
    @JsonProperty("carbohydrates_total_g")
    private double carbohydrates;
    @JsonProperty("calories")
    private double calories;
}
