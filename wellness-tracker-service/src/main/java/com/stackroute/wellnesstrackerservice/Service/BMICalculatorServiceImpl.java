package com.stackroute.wellnesstrackerservice.Service;

import com.stackroute.wellnesstrackerservice.Model.BMI;
import org.springframework.stereotype.Service;

@Service
public class BMICalculatorServiceImpl implements BMICalculatorService{
    @Override
    public double calculate(BMI bmi) {
        double index;
        double weight = bmi.getWeight();
        double height = bmi.getHeight();
        if ((bmi.getHeight() <= 0) || (bmi.getWeight() <= 0)) {
            throw new IllegalArgumentException("Arguments must be above 0");
        }
        if(bmi.getWeightUnit().equals("kg")){
            weight= bmi.getWeight()*2.20462;
        }
        if(bmi.getHeightUnit().equals("ft")){
            height=bmi.getHeight()*12;
        }
        if(bmi.getHeightUnit().equals("cm")){
            height=bmi.getHeight()*0.393701;
        }
        index=((weight*703)/(height*height));
        return index;
    }
}
