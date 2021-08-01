package com.stackroute.wellnessmentorservice.service;

import com.stackroute.wellnessmentorservice.exception.MentorAlreadyExistException;
import com.stackroute.wellnessmentorservice.exception.MentorNotExistException;
import com.stackroute.wellnessmentorservice.model.Mentor;
import com.stackroute.wellnessmentorservice.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServiceImpl implements MentorService {

    private MentorRepository mentorRepository;

    @Autowired
    public MentorServiceImpl(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    //    this is to add new mentor to database
    @Override
    public boolean saveMentor(Mentor mentor) throws MentorAlreadyExistException {
        if (!mentorRepository.findById(mentor.getEmailId()).isPresent()) {
            try {
                mentorRepository.save(mentor);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            throw new MentorAlreadyExistException("mentor already exist");
        }
    }

    //    this is to update mentor to database
    @Override
    public boolean updateMentor(Mentor mentor) throws MentorNotExistException {
        if (mentorRepository.findById(mentor.getEmailId()).isPresent()) {
            try {
                mentorRepository.save(mentor);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            throw new MentorNotExistException("mentor not found exception");
        }
    }
    //    this is to get all mentors from database
    @Override
    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }


    //    this is to get mentor from database for particular email
    @Override
    public Mentor getMentorByEmail(String email) throws MentorNotExistException  {
        if (mentorRepository.findById(email).isPresent()) {
            try {
                return mentorRepository.findById(email).get();
            } catch (Exception e) {
                return null;
            }
        } else {
            throw new MentorNotExistException("mentor not found exception");
        }
    }



}