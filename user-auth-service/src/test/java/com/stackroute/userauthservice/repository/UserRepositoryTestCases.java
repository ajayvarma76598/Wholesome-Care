package com.stackroute.userauthservice.repository;


import com.stackroute.userauthservice.model.User;
import com.stackroute.userauthservice.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.After;
import org.junit.Assert;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTestCases {
//    @Autowired
//    private UserRepository userRepository;
//
//    private User user;
//    @BeforeEach
//    public void setUp()throws Exception {
//        user = new User();
//        user.setId(1L);
//        user.setName("abhishek");
//        user.setEmail("abhishek@gmail.com");
//        user.setPassword("abhishek@123");
//        user.setMembership("free");
//
//
//    }


//    @After
//    public void tearDown() throws Exception {
//        userRepository.deleteAll();
//    }

//    @Test
//    public void testRegisterUserSuccess() {
//        userRepository.save(user);
//        User object = userRepository.findByEmail(user.getEmail());
//        Assert.assertEquals(user.getEmail(), object.getEmail());
//    }
//
//    @Test
//    public void testLoginUserSuccess() {
//        userRepository.save(user);
//        User object = userRepository.findByEmail(user.getEmail());
//        Assert.assertEquals(user.getEmail(), object.getEmail());
//    }


}
