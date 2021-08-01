package com.stackroute.graphqueryservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {
    private String email;
    private String name;
    private Date date;
    private String age;
    private String place;
    private String imageUrl;
    private String physicalLevel;
    private String mentalLevel;
    private String dietLevel;
}
