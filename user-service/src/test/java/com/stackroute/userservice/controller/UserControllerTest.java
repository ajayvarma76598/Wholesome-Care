package com.stackroute.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

//
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private User user;
//    @MockBean
//    UserService userService;
//    @InjectMocks
//    UserController userController;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//        user = new User();
//        user.setEmail("Jhon123@gmail.com");
//        user.setName("Jhon Simon");
//        user.setPassword("abhishek");
//        user.setRole("USER");
//    }
//
//    @Test
//    public void registerUserSuccess() throws Exception {
//
//        when(userService.registerUser(user)).thenReturn(user);
//        mockMvc.perform(post("/api/v1/register")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void registerUserFailure() throws Exception {
//
//        when(userService.registerUser(any())).thenThrow(UserAlreadyExistsException.class);
//        mockMvc.perform(post("/api/v1/register")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
//
//    }
//
//
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
