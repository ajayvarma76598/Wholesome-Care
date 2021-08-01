package com.stackroute.userauthservice.controller;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userauthservice.model.User;
import com.stackroute.userauthservice.repo.UserRepository;
import com.stackroute.userauthservice.service.UserService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.any;
@RunWith(SpringRunner.class)
@ExtendWith({ SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs
public class UserControllerTests {




//    @Mock
//    private UserService userService;
//    private User user;


//    @InjectMocks
//    private UserController userController;
//
//    @Mock
//    private UserRepository userRepository;

//    @BeforeEach
//    public void setUp(){
//        User user=new User();
//        user.setId(1L);
//        user.setName("Abhishek");
//        user.setEmail("abhishek@gmail.com");
//        user.setPassword("abhishek@123");
//        user.setMembership("free");
//
//        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
//    }

//    private MockMvc mockMvc;
//    @Autowired
//    private WebApplicationContext context;
//
//    @Rule
//    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
//
//    @Before
//    public void setUp(){
//
//        mockMvc = MockMvcBuilders.webAppContextSetup(context)
//                .apply(documentationConfiguration(this.restDocumentation)).build();
//    }
//    @Test
//    public void testValidRole() throws Exception {
//
//        mockMvc.perform(get("/api/all"))
//
//                .andExpect(status().isOk()).
//                andExpect(MockMvcResultMatchers.content().string("Public content goes here"))
//                .andDo(print())
//                .andDo(document("get-details"
//                        ,preprocessRequest(prettyPrint())
//                        ,preprocessResponse(prettyPrint())));
    }











