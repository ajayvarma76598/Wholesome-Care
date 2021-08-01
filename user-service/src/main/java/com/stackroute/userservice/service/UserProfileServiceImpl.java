package com.stackroute.userservice.service;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.*;
import com.stackroute.userservice.repository.UserProfileRepository;
import com.stackroute.userservice.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
    public class UserProfileServiceImpl implements UserProfileService{
    private UserProfileRepository userProfileRepository;
    private UserRepository userRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository,UserRepository userRepository){
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;

    }
    @Override
    public UserProfile getUserByEmailId(String email) throws UserNotFoundException {
        System.out.println(email);
       Optional<UserProfile> user = userProfileRepository.findByEmail(email);
//        UserProfile user = userProfileRepository.findByEmail(email);
        System.out.println(user);
//        if(!user.isPresent()){
        if(user==null){
            throw new UserNotFoundException("UserNotFoundException");
        }
        return user.get();
    }
    @Override
    public UserProfile updateUserByEmailId(JSONObject user) throws UserNotFoundException {
        System.out.println("u"+ user.toString());
        UserProfile updateUser=userProfileRepository.findByEmail(user.getString("email")).get();
        User userup=userRepository.findByEmail(user.getString("email")).get();
        System.out.println("u1"+ updateUser);

        updateUser.setName(user.getString("name"));
        updateUser.setPlace(user.getString("place"));
        updateUser.setAge(user.getString("age"));

        userup.setName(user.getString("name"));
        userup.setPlace(user.getString("place"));
        userup.setAge(user.getString("age"));
        userRepository.save(userup);

        if(updateUser==null){
            throw new UserNotFoundException("UserNotFoundException");
        }

        return userProfileRepository.save(updateUser);

    }

    public List<UserProfile> getListofProfiles(){
        return userProfileRepository.findAll();
    }

    public void  updateSomething(UserProfile userProfile){
        Plan plan= userProfile.getPlan();
        List<Day> days = new ArrayList<>();
        days= userProfile.getDays();
        Day day = new Day();
        day.setLocalDate(LocalDate.now());
        day.setPlan(plan);
        days.add(day);
        List<Appointment> appointments = new ArrayList<>();
        userProfile.setAppointments(appointments);
        userProfile.setDays(days);
        userProfileRepository.save(userProfile);
    }

    public UserProfile  updateDaysInUserProfile(UserProfile userProfile){
        UserProfile userProfile1 = userProfileRepository.findByEmail(userProfile.getEmail()).get();
        userProfile1.setDays(userProfile.getDays());
        return userProfileRepository.save(userProfile1);
    }
//    public UserProfile updateAppointmentInUserProfile(List<Appointment> appointments, String email){
//        UserProfile userProfile1 = userProfileRepository.findByEmail(email).get();
//        if (userProfile1.getPlanType().equalsIgnoreCase("PLATINUM")){
//            List<Appointment> appointmentList = new ArrayList<>();
//            appointmentList = appointments;
//            userProfile1.setAppointments(appointmentList);
//            return userProfileRepository.save(userProfile1);
//        }
//        return userProfile1;
//    }

    public Boolean checkUserIsPresent(String email){
        return userProfileRepository.findByEmail(email).isPresent();
    }

    public UserProfile updateAppointmentInUserProfile(UserProfile userProfile){
        UserProfile userProfile1 = userProfileRepository.findByEmail(userProfile.getEmail()).get();
        if (userProfile1.getPlanType().equalsIgnoreCase("PLATINUM")){
            List<Appointment> appointments = new ArrayList<>();
            appointments = userProfile.getAppointments();
            userProfile1.setAppointments(appointments);
            return userProfileRepository.save(userProfile1);
        }
        return userProfile1;
    }

}