package com.stackroute.wellnessmentorservice.repository;

import com.stackroute.wellnessmentorservice.model.Mentor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MentorRepository extends MongoRepository<Mentor,String> {
}
