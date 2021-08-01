package com.stackroute.wellnessservice.controller;

import com.stackroute.wellnessservice.Repository.GuestUserRepository;
import com.stackroute.wellnessservice.Repository.HelplineRepository;
import com.stackroute.wellnessservice.model.GuestUser;
import com.stackroute.wellnessservice.model.Helpline;
import com.stackroute.wellnessservice.service.GuestUserService;
import com.stackroute.wellnessservice.service.HelplineService;
import com.stackroute.wellnessservice.service.MailService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs
public class GuestUserController {
    private MockMvc mockMvc;
    private GuestUser guestUser;
    private Helpline helpline;
    @Autowired
    private WebApplicationContext context;

    @Mock
    private GuestUserService guestUserService;
    @Mock
    private GuestUserRepository guestUserRepository;
    @Mock
    private HelplineService helplineService;
    @Mock
    private HelplineRepository helplineRepository;
    @Mock
    private MailService mailService;

    @InjectMocks
    private GuestUserController guestUserController;


    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-docs");
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(guestUserController).build();
        guestUser=new GuestUser();
        guestUser.setName("hema");
        guestUser.setEmail("hema@gmail.com");
        guestUser.setPhoneNumber("998989898");
        guestUser.setAddress("pune");

        helpline=new Helpline();
        helpline.setCityName("pune");
        helpline.setHelplineNumber("6798463");
    }
   /* @Test
    public void saveUserSuccess() throws Exception{
        when(guestUserRepository.getUserByEmail("hema.t9991@gmail.com")).thenReturn(guestUser);
        mockMvc.perform(get("/api/v1/guestUser"))
                .andExpect(status().isOk()).andDo(print())
                .andDo(document("save user "
                        ,preprocessRequest(prettyPrint())
                        ,preprocessResponse(prettyPrint())));
    }
    @Test
    public void saveHelplineSuccess() throws Exception{
        when(helplineRepository.getHelplineByCityName("Pune")).thenReturn(helpline);
        mockMvc.perform(get("/api/v1/helpline"))
                .andExpect(status().isOk()).andDo(print())
                .andDo(document("helpline "
                        ,preprocessRequest(prettyPrint())
                        ,preprocessResponse(prettyPrint())));
    }*/
}
