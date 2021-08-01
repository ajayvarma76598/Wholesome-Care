package com.stackroute.userservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class User implements Serializable {


    private static final long serialVersionUID = 65981149772133526L;
    @Id
    private String email;
    private String name;
    private String password;
    private String role="ROLE_USER";
    private String age;
    private String place;
    private String imageUrl;
    private String gender;


    // bi-directional many-to-many association to Role


}
