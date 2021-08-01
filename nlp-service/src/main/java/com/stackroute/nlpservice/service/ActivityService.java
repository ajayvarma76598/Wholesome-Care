package com.stackroute.nlpservice.service;

import com.stackroute.nlpservice.model.Activity;
import java.util.List;
public interface ActivityService {
    public List<Activity> getCalorieBurnt(String activityDetail);
}
