package com.stackroute.userauthservice.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stackroute.userauthservice.dto.JwtAuthenticationResponse;
import com.stackroute.userauthservice.dto.LocalUser;
import com.stackroute.userauthservice.dto.LoginRequest;
import com.stackroute.userauthservice.security.jwt.TokenProvider;
import com.stackroute.userauthservice.service.UserService;
import com.stackroute.userauthservice.util.GeneralUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import junit.framework.Assert;

class AuthControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//    @Mock
//    private UserService userService;
//    private User user;
//
//    private AuthController authController;
//    @Autowired
//    AuthenticationManager authenticationManager;
//    @Mock
//    private LoginRequest loginRequest;
//    @Autowired
//    TokenProvider tokenProvider;
 


//    @Test
//    public void testLoginUser() throws Exception {
        
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = tokenProvider.createToken(authentication);
//        LocalUser localUser = (LocalUser) authentication.getPrincipal();
//       ResponseEntity<?> r  = authController.authenticateUser(loginRequest);
//       System.out.println(r);
//        Assert.assertEquals(ResponseEntity.ok(),r);
//        mockMvc.perform(post("/api/auth/signin")
//                .content(asJsonString(loginRequest)))
//                .andExpect(status().isOk());
//                verify(userService,times(1)).registerUser(any());

//        mockMvc.perform(post("/api/user/auth").contentType(MediaType.APPLICATION_JSON)
//    }
//    public static String asJsonString(final Object obj){
//        try{
//            return new ObjectMapper().writeValueAsString(obj);
//
//
//        } catch (Exception e) {
//throw new RuntimeException(e);
//        }
 
	
//	 @Autowired
//	   private MockMvc mvc;
//
//	   @MockBean
//	   private AuthController authController;
//	   @Autowired
//		AuthenticationManager authenticationManager;
//
//	   @Test
//	   public void getArrivals() throws Exception {
//			LoginRequest loginRequest=new LoginRequest();
//	        loginRequest.setEmail("abhishek93264@gmail.com");
//	        loginRequest.setPassword("abhishek@123");
//	        AuthenticationManager am = new AuthenticationManager();

//		   Authentication auth = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
//			SecurityContextHolder.getContext().setAuthentication(auth);
//			String jwt = tokenProvider.createToken(auth);
//			LocalUser localUser = (LocalUser) auth.getPrincipal();
//			ResponseEntity.ok(new JwtAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(localUser)));
//			mvc.perform(MockMvcRequestBuilders.get("/api/auth/signin").header("Authorization",jwt)).andExpect(status().isOk());
//       Assert.assertEquals(jwt, ResponseEntity.ok(), ResponseEntity.ok());

	   

//	@BeforeEach
//	    public void setUp(){
//	    	        mvc= MockMvcBuilders.standaloneSetup(authController).build();


//	    }






}


