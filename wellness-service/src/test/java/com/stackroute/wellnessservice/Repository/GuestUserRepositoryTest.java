package com.stackroute.wellnessservice.Repository;

import com.stackroute.wellnessservice.model.GuestUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataMongoTest
public class GuestUserRepositoryTest {
    @Autowired
    private GuestUserRepository guestUserRepository;
    private GuestUser guestUser;

    @Before
    public void setUp() throws Exception {
        guestUser=new GuestUser();
        guestUser.setName("hema");
        guestUser.setEmail("hema@gmail.com");
        guestUser.setPhoneNumber("998989898");
        guestUser.setAddress("pune");
    }

    @After
    public void tearDown() throws Exception {

        guestUserRepository.deleteAll();
    }
    @Test
    public void saveUserTest() {

        guestUserRepository.insert(guestUser);
        GuestUser fetchedUser = guestUserRepository.findById("hema").get();
        Assert.assertEquals(guestUser.getName(), fetchedUser.getName());

    }

}
