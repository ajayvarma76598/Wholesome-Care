package com.stackroute.graphcommandservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreBelt {
    private String wellnessType;
    private Double totalPercentage;
    private String beltColour;
}
