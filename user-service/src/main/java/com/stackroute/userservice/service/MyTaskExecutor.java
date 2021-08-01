package com.stackroute.userservice.service;

import com.stackroute.userservice.model.Activities;

import com.stackroute.userservice.model.UserProfile;

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
    UserProfileServiceImpl userService;
    volatile boolean isStopIssued;


    public void startExecutionAt(int targetHour, int targetMin, int targetSec)
    {
        Runnable taskWrapper = new Runnable(){

            @Override
            public void run()
            {
                List<UserProfile> list=userService.getListofProfiles();
                int count = (int)list.stream().count();
                for (int i=0;i<count;i++){
                    UserProfile userProfile = list.get(i);
                    userService.updateSomething(userProfile);
                    System.out.println("Running "+i);
                    System.out.println(userProfile.getEmail());
                }
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

//    public void stop()
//    {
//        executorService.shutdown();
//        try {
//            executorService.awaitTermination(1, TimeUnit.DAYS);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(MyTaskExecutor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
