package com.stackroute.wellnesstrackerservice.Model;

import lombok.Data;

@Data
public class BMI {
    private double weight;
    private String weightUnit;
    private double height;
    private String heightUnit;
}
