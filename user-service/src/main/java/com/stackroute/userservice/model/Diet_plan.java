package com.stackroute.userservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Diet_plan {
    private List<FoodItems> breakfast;
    private List<FoodItems> morningSnacks;
    private List<FoodItems> lunch;
    private List<FoodItems> eveningSnacks;
    private List<FoodItems> dinner;
}
