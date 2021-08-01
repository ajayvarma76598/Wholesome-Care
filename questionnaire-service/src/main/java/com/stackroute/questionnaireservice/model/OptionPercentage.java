package com.stackroute.questionnaireservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionPercentage {
    private Integer questionId;
    private String options;
    private Integer percentage;
    public OptionPercentage(Integer questionId, String options){
        this.questionId = questionId;
        this.options = options;
    }
}
