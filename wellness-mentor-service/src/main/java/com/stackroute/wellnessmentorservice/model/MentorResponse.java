package com.stackroute.wellnessmentorservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MentorResponse {
    private String emailId;
    private String name;
    private String expertize;
}
