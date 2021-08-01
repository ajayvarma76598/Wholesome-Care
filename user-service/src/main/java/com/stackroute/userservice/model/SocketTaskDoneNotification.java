package com.stackroute.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocketTaskDoneNotification {
    private String name;
    private List<Activities> activitiesDoneList;
}
