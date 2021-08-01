package com.stackroute.userservice.repository;

import com.stackroute.userservice.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setEmail("xyz@gmail.com");
        user.setPassword("123@xyz");


    }

    @After
    public void tearDown() throws Exception {

        userRepository.deleteAll();

    }

    @Test
    public void registerUserTest() {
        try {
            userRepository.insert(user);
            User fetcheduser = userRepository.findById("xyz@gmail.com").get();
            Assert.assertEquals(user.getEmail(), fetcheduser.getEmail());
        } catch (Exception e) {
        }
    }

}