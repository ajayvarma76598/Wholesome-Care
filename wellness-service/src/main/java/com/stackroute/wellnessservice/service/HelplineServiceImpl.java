package com.stackroute.wellnessservice.service;

import com.stackroute.wellnessservice.Repository.HelplineRepository;
import com.stackroute.wellnessservice.exception.GuestUserNotCreatedException;
import com.stackroute.wellnessservice.exception.HelplineNotFoundException;
import com.stackroute.wellnessservice.model.Helpline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelplineServiceImpl implements HelplineService{
    private HelplineRepository helplineRepository;

    @Autowired
    public HelplineServiceImpl(HelplineRepository helplineRepository) {
        this.helplineRepository = helplineRepository;
    }

    @Override
    public Helpline saveHelpline(Helpline helpline) throws HelplineNotFoundException {
        Helpline helplineCreated=helplineRepository.save(helpline);
        if(helplineCreated!=null){
            return helplineCreated;
        }
        throw new HelplineNotFoundException("helpline details are not entered");
    }
}
