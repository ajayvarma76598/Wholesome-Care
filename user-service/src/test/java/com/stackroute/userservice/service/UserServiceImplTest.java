package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


public class UserServiceImplTest {

//    @Mock
//    UserRepository userRepository;
//
//    User user;
//
//    @InjectMocks
//    UserServiceImpl userService;
//
//    List<User> userList = null;
//    Optional<User> options;
//
//    @Before
//    public void setup(){
//        MockitoAnnotations.initMocks(this);
//        user = new User();
//        user.setEmail("xyz@gmail.com");
//
//        user.setPassword("123@xyz");
//
//        options = Optional.of(user);
//    }
//    @Test
//    public void registerUserSuccess() throws UserAlreadyExistsException {
//
//        when(userRepository.insert((User) any())).thenReturn(user);
//        User userSaved = userService.registerUser(user);
//        assertEquals(user, userSaved);
//
//    }
//
//    @Test
//    public void registerUserFailure() throws UserAlreadyExistsException {
//        when(userRepository.insert((User) any())).thenReturn(null);
//        User userSaved = userService.registerUser(user);
//        assertEquals(user, userSaved);
//
//    }

}
