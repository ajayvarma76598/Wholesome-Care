package com.stackroute.userauthservice.service;

import com.stackroute.userauthservice.model.User;
import com.stackroute.userauthservice.repo.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private User user;
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    Optional<User> optional;


//    @BeforeEach
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//
//
//        user = new User();
//        user.setId(1L);
//        user.setName("abhishek");
//        user.setEmail("abhishek@gmail.com");
//        user.setPassword("abhishek@123");
//        user.setMembership("free");
//        optional = Optional.of(user);
//
//
//
//    }
//
//    @Test
//    public void testFindByEmail()  {
//        Mockito.when(userRepository.findByEmail("abhishek@gmail.com")).thenReturn(user);
//        User user2 = userServiceImpl.findUserByEmail("abhishek@gmail.com");
//        Assert.assertEquals(user.getEmail(), user2.getEmail());
//    }
   

}




