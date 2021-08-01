package com.stackroute.userservice.service;

import com.stackroute.userservice.model.Appointment;
import com.stackroute.userservice.model.CustomizedPlan;
import com.stackroute.userservice.model.Day;
import com.stackroute.userservice.model.UserProfile;
import com.stackroute.userservice.repository.CustomizedPlanRepository;
import com.stackroute.userservice.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class ConsumerService implements RabbitListenerConfigurer {
    private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);


    private UserProfileService userService;


    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private CustomizedPlanRepository customizedPlanRepository;

    @RabbitListener(queues = "${spring.rabbitmq.queue1}")
    public void receivedMessage(CustomizedPlan userProfile)   {
        UserProfile userProfile1 = new UserProfile();
        userProfile1.setEmail(userProfile.getEmail());
        userProfile1.setAge(userProfile.getAge());
        userProfile1.setCreationDate(userProfile.getDate());
        userProfile1.setName(userProfile.getName());
        userProfile1.setPlace(userProfile.getPlace());
        userProfile1.setImageUrl(userProfile.getImageUrl());
        userProfile1.setPlanType(userProfile.getPlanType());
        userProfile1.setPhysicalLevel(userProfile.getPhysicalLevel());
        userProfile1.setMentalLevel(userProfile.getMentalLevel());
        userProfile1.setDietLevel(userProfile.getDietLevel());
        userProfile1.setFollowers(userProfile.getFollowers());
        userProfile1.setFollowing(userProfile.getFollowing());
        userProfile1.setMentorList(userProfile.getMentorList());
        userProfile1.setPlan(userProfile.getPlan());
        userProfile1.setPhysicalScore(userProfile.getPhysicalScore());
        userProfile1.setMentalScore(userProfile.getMentalScore());
        userProfile1.setDietScore(userProfile.getDietScore());
        List<Day> days = new ArrayList<>();
        Day day = new Day(LocalDate.now(),userProfile.getPlan());
        days.add(day);
        userProfile1.setDays(days);
        List<Appointment> appointments = new ArrayList<>();
        userProfile1.setAppointments(appointments);
        userProfileRepository.save(userProfile1);
        logger.info("user is " + userProfile);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
