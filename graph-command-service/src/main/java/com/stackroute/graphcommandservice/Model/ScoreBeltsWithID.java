package com.stackroute.graphcommandservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreBeltsWithID {
    private String email;
    private String planType;
    private List<ScoreBelt> scoreBeltList;
}
