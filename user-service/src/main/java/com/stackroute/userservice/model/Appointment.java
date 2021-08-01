package com.stackroute.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {
    private AppointmentSlot appointment;
    private String mentorEmail;
    private String mentorName;
    private String mentorExpertize;
}
