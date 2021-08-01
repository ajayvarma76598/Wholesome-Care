package com.stackroute.nlpservice.service;

import com.stackroute.nlpservice.model.Nutrient;
import java.util.List;
public interface NlpService {
    String getJsonResponseString(String foodConsumed);
    List<Nutrient> getIngredientInformationOfFoodConsumed(String foodConsumed);
}
