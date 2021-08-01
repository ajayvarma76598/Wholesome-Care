package com.stackroute.wellnessservice.service;

import com.stackroute.wellnessservice.Repository.GuestUserRepository;
import com.stackroute.wellnessservice.exception.GuestUserNotCreatedException;
import com.stackroute.wellnessservice.model.GuestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestUserServiceImpl implements GuestUserService{
    private GuestUserRepository guestUserRepository;

    @Autowired
    public GuestUserServiceImpl(GuestUserRepository guestUserRepository){
        this.guestUserRepository=guestUserRepository;
    }
    @Override
    public GuestUser saveUser(GuestUser guestUser) throws GuestUserNotCreatedException{
        GuestUser guestUserCreated=guestUserRepository.save(guestUser);
        if(guestUserCreated!=null){
            return guestUserCreated;
        }
        throw new GuestUserNotCreatedException("Guest user not entered details");
    }
}
