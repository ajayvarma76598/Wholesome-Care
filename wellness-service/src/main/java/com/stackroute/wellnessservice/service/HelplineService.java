package com.stackroute.wellnessservice.service;

import com.stackroute.wellnessservice.exception.HelplineNotFoundException;
import com.stackroute.wellnessservice.model.Helpline;
import org.springframework.stereotype.Service;

@Service
public interface HelplineService {
    Helpline saveHelpline(Helpline helpline) throws HelplineNotFoundException;
}
