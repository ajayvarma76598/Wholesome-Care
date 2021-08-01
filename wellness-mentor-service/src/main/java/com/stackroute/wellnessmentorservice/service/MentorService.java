package com.stackroute.wellnessmentorservice.service;

import com.stackroute.wellnessmentorservice.exception.MentorAlreadyExistException;
import com.stackroute.wellnessmentorservice.exception.MentorNotExistException;
import com.stackroute.wellnessmentorservice.model.Mentor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MentorService {
    public boolean saveMentor(Mentor mentor)throws MentorAlreadyExistException;
    public  boolean updateMentor(Mentor mentor)throws MentorNotExistException;
    public List<Mentor> getAllMentors();
    public Mentor getMentorByEmail(String email)throws MentorNotExistException;
}
