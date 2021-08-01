package com.stackroute.wellnessservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document
public class GuestUser {
    @Id
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

}
