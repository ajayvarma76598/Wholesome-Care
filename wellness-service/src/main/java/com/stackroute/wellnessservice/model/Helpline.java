package com.stackroute.wellnessservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document
public class Helpline {
    @Id
    private String cityName;
    private String helplineNumber;
}
