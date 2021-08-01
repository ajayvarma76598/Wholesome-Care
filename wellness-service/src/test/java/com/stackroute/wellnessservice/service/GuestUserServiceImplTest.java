package com.stackroute.wellnessservice.service;

import com.stackroute.wellnessservice.Repository.GuestUserRepository;
import com.stackroute.wellnessservice.exception.GuestUserNotCreatedException;
import com.stackroute.wellnessservice.model.GuestUser;
import com.stackroute.wellnessservice.model.Helpline;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GuestUserServiceImplTest {
    private GuestUser guestUser;
    @Mock
    private GuestUserRepository guestUserRepository;
    @InjectMocks
    private GuestUserServiceImpl guestUserServiceImpl;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        guestUser=new GuestUser();
        guestUser.setName("hema");
        guestUser.setEmail("hema@gmail.com");
        guestUser.setPhoneNumber("998989898");
        guestUser.setAddress("pune");

    }

    @Test
    public void saveUserTestSuccess() throws Exception {

        when(guestUserRepository.save(any())).thenReturn(guestUser);
        GuestUser savedGuestUser = guestUserServiceImpl.saveUser(guestUser);
        verify(guestUserRepository,times(1)).save(any());
    }

    @Test(expected = GuestUserNotCreatedException.class)
    public void saveUserTestFailure() throws Exception {

        when(guestUserRepository.insert((GuestUser) any())).thenReturn(null);
        GuestUser savedGuestUser = guestUserServiceImpl.saveUser(guestUser);
        Assert.assertEquals(guestUser, savedGuestUser);

    }


}
