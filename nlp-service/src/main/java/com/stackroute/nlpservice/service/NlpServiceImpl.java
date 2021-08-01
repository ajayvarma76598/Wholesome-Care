package com.stackroute.nlpservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.nlpservice.model.Activity;
import com.stackroute.nlpservice.model.Nutrient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
//creating a service class implements NlpService
@Service
public class NlpServiceImpl implements NlpService{
    @Value("${nutrition.api.url}")
    private String URL;
    @Value("${nutrition.api.headerApiKey}")
    private String headerApiKey;
    @Value("${nutrition.api.headerApiValue}")
    private String headerApiValue;
    @Value("${nutrition.api.jsonArray.name}")
    private String foodName;
    @Value("${nutrition.api.jsonArray.serving_size_g}")
    private String servingSizeGram;
    @Value("${nutrition.api.jsonArray.sugar_g}")
    private String sugar;
    @Value("${nutrition.api.jsonArray.fiber_g}")
    private String fiber;
    @Value("${nutrition.api.jsonArray.sodium_mg}")
    private String sodium;
    @Value("${nutrition.api.jsonArray.potassium_mg}")
    private String potassium;
    @Value("${nutrition.api.jsonArray.fat_saturated_g}")
    private String fatSaturate;
    @Value("${nutrition.api.jsonArray.fat_total_g}")
    private String fat;
    @Value("${nutrition.api.jsonArray.cholesterol_mg}")
    private String cholesterol;
    @Value("${nutrition.api.jsonArray.protein_g}")
    private String protein;
    @Value("${nutrition.api.jsonArray.carbohydrates_total_g}")
    private String carbohydrates;
    @Value("${nutrition.api.jsonArray.calories}")
    private String calories;
    private RestTemplate restTemplate;
    @Autowired
    public NlpServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    //method for calling the external api and return the response as String Json
    @Override
    public String getJsonResponseString(String foodConsumed){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(headerApiKey, headerApiValue);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL+foodConsumed, HttpMethod.GET, httpEntity, String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }
    //create a method that takes foodConsumed details of user in String
    //return list of Nutrient
    @Override
    public List<Nutrient> getIngredientInformationOfFoodConsumed(String foodConsumed) {
        List<Nutrient> nutrientList = new ArrayList<>();
        String jsonResponseString = getJsonResponseString(foodConsumed);
        JSONObject jsonObject = new JSONObject(jsonResponseString);
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for(int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            Nutrient nutrient = new Nutrient();
            nutrient.setFoodName(jsonObject1.getString(foodName));
            nutrient.setServingSizeGram(jsonObject1.getDouble(servingSizeGram));
            nutrient.setSugar(jsonObject1.getDouble(sugar));
            nutrient.setFiber(jsonObject1.getDouble(fiber));
            nutrient.setSodium(jsonObject1.getDouble(sodium)*0.001);
            nutrient.setPotassium(jsonObject1.getDouble(potassium)*0.001);
            nutrient.setFatSaturate(jsonObject1.getDouble(fatSaturate));
            nutrient.setFat(jsonObject1.getDouble(fat));
            nutrient.setCholesterol(jsonObject1.getDouble(cholesterol)*0.001);
            nutrient.setProtein(jsonObject1.getDouble(protein));
            nutrient.setCarbohydrates(jsonObject1.getDouble(carbohydrates));
            nutrient.setCalories(jsonObject1.getDouble(calories));
            nutrientList.add(nutrient);
        }
        return nutrientList;
    }
}
