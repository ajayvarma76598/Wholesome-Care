package com.stackroute.graphcommandservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;


@Data
@Node
public class User {

    @Id
    private String email;
    private String password;
    private String role;
    private String name;
    private String age;
    private String place;
    private String gender;
    private String imageUrl;
}
