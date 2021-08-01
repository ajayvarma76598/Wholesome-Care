package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.Appointment;
import com.stackroute.userservice.model.UserProfile;
import org.json.JSONObject;

import java.util.List;

public interface UserProfileService {
    UserProfile getUserByEmailId(String email) throws UserNotFoundException;
    UserProfile updateUserByEmailId(JSONObject user) throws UserNotFoundException;
    public UserProfile  updateDaysInUserProfile(UserProfile userProfile);
//    public UserProfile updateAppointmentInUserProfile(List<Appointment> appointments, String email);
    public UserProfile updateAppointmentInUserProfile(UserProfile userProfile);
public Boolean checkUserIsPresent(String email);
}
