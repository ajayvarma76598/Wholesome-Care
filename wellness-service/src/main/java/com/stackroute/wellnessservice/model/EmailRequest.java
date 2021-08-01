package com.stackroute.wellnessservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document
public class EmailRequest {
    private String to;
    private String subject;
    private String message;
    private String cityName;


}
