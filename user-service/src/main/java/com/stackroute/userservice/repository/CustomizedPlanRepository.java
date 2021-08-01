package com.stackroute.userservice.repository;

import com.stackroute.userservice.model.CustomizedPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomizedPlanRepository extends MongoRepository<CustomizedPlan, String> {
}
