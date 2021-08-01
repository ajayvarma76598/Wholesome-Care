package com.stackroute.wellnessmentorservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookedAppointment {
    private AppointmentSlot appointmentSlot;
    private String customerEmail;
    private String customerName;
}
