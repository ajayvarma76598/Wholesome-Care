package com.stackroute.wellnessmentorservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "mentor")
public class Mentor {
    @Id
    private String emailId;
    private String name;
    private String expertize;
    private int rating;
    private String imagePath;
    private String about;
    private String education;
    private String certification;
    private String training;
    private String experience;
    private String language;
    private String specialities;
    private AppointmentSlot[] appointmentSlots;
    private BookedAppointment[] bookedAppointments;
}
