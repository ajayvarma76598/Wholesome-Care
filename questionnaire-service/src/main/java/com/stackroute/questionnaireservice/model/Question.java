package com.stackroute.questionnaireservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Integer questionId;
    private String questionType;
    private String questionText;
    private List<String> questionOptions;
}
