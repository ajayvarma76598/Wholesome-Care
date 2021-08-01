package com.stackroute.wellnessservice.service;

import com.stackroute.wellnessservice.Repository.GuestUserRepository;
import com.stackroute.wellnessservice.Repository.HelplineRepository;
import com.stackroute.wellnessservice.exception.GuestUserNotCreatedException;
import com.stackroute.wellnessservice.exception.HelplineNotFoundException;
import com.stackroute.wellnessservice.model.GuestUser;
import com.stackroute.wellnessservice.model.Helpline;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class HelplineServiceImplTest {
    private Helpline helpline;
    @Mock
    private HelplineRepository helplineRepository;
    @InjectMocks
    private HelplineServiceImpl helplineServiceImpl;
    Optional<Helpline> options;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        helpline=new Helpline();
        helpline.setCityName("pune");
        helpline.setHelplineNumber("6798463");
        options = Optional.of(helpline);

    }

    @Test
    public void saveHelplineTestSuccess() throws Exception {

        when(helplineRepository.save(any())).thenReturn(helpline);
        Helpline savedHelpline = helplineServiceImpl.saveHelpline(helpline);
        verify(helplineRepository,times(1)).save(any());

    }

    @Test(expected = HelplineNotFoundException.class)
    public void saveHelplineTestFailure() throws Exception {

        when(helplineRepository.insert((Helpline) any())).thenReturn(null);
        Helpline savedHelpline = helplineServiceImpl.saveHelpline(helpline);
        Assert.assertEquals(helpline, savedHelpline);
    }





}
