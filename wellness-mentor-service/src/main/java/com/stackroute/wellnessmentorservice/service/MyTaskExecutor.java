package com.stackroute.wellnessmentorservice.service;


import com.stackroute.wellnessmentorservice.model.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MyTaskExecutor
{
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    @Autowired
    MentorServiceImpl mentorService;
    volatile boolean isStopIssued;


    public void startExecutionAt(int targetHour, int targetMin, int targetSec)
    {
        Runnable taskWrapper = new Runnable(){

            @Override
            public void run()
            {
                List<Mentor> list=mentorService.getAllMentors();
               list.stream().forEach(mentor -> {
                   mentor.setBookedAppointments(null);
                   mentorService.updateMentor(mentor);
               });
                startExecutionAt(targetHour, targetMin, targetSec);
            }

        };
        long delay = computeNextDelay(targetHour, targetMin, targetSec);
        executorService.schedule(taskWrapper, delay, TimeUnit.SECONDS);
    }

    private long computeNextDelay(int targetHour, int targetMin, int targetSec)
    {
        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.systemDefault();
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedNextTarget = zonedNow.withHour(targetHour).withMinute(targetMin).withSecond(targetSec);
        if(zonedNow.compareTo(zonedNextTarget) > 0)
            zonedNextTarget = zonedNextTarget.plusDays(1);

        Duration duration = Duration.between(zonedNow, zonedNextTarget);
        return duration.getSeconds();
    }


}
