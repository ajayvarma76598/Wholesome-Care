package com.stackroute.wellnessservice.service;

import com.stackroute.wellnessservice.exception.GuestUserNotCreatedException;
import com.stackroute.wellnessservice.model.GuestUser;
import org.springframework.stereotype.Service;

@Service
public interface GuestUserService {
    GuestUser saveUser(GuestUser guestUser) throws GuestUserNotCreatedException;
}
