package com.stackroute.questionnaireservice.controller;

import com.stackroute.questionnaireservice.exception.QuestionsNotFoundException;
import com.stackroute.questionnaireservice.model.Question;
import com.stackroute.questionnaireservice.service.QuestionnaireService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs
public class QuestionnaireControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @Mock
    private QuestionnaireService questionnaireService;
    List<Question> questions;
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-docs");
    @Before
    public void setUp(){
        questions = new ArrayList<>();
        questions.add(new Question(1, "Physical Wellness", "\"I generally feel very good about my physical health (1 being strongly disagree to 5 being strongly agree)\"", new ArrayList<String>( Arrays.asList("1","2","3","4","5","0"))));
        questions.add(new Question(2, "Physical Wellness", "\"I exercise at least three times a week (1 being strongly disagree to 5 being strongly agree)\"", new ArrayList<String>( Arrays.asList("1","2","3","4","5","0"))));
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(this.restDocumentation)).build();
    }

    @Test
    public void getCalorieBurntSuccess() throws Exception, QuestionsNotFoundException {
        when(questionnaireService.getAllQuestions()).thenReturn(questions);
        mockMvc.perform(get("/api/v1/questions"))
//                .param("activityDetail", activityDetail))
                .andExpect(status().isOk()).andDo(print())
                .andDo(document("get-questions"
                        ,preprocessRequest(prettyPrint())
                        ,preprocessResponse(prettyPrint())));
    }

//    @Test
//    public void getCalorieBurntTimeNotGetFailure() throws Exception {
//        when(activityService.getCalorieBurnt(any())).thenThrow(NoTimeGivenException.class);
//        mockMvc.perform(get("/api/v1/calorieBurnt")
//                .param("activityDetail", "min dancing")
//                .contentType(MediaType.TEXT_PLAIN))
//                .andExpect(status().isConflict())
//                .andExpect(MockMvcResultMatchers.content().string("Please give activity time."))
//                .andDo(print())
//                .andDo(document("handle-NoTimeGivenException"
//                        ,preprocessRequest(prettyPrint())
//                        ,preprocessResponse(prettyPrint())));
//    }
//    @Test
//    public void getCalorieBurntTimeUnitNotGetFailure() throws Exception {
//        when(activityService.getCalorieBurnt(any())).thenThrow(NoTimeUnitGivenException.class);
//        mockMvc.perform(get("/api/v1/calorieBurnt")
//                .param("activityDetail", "1 yoga")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isConflict())
//                .andExpect(MockMvcResultMatchers.content().string("Please give proper activity time unit."))
//                .andDo(print())
//                .andDo(document("handle-NoTimeUnitGivenException"
//                        ,preprocessRequest(prettyPrint())
//                        ,preprocessResponse(prettyPrint())));
//    }
//    @Test
//    public void getCalorieBurntNoActivityMatchFromDataSetFailure() throws Exception {
//        when(activityService.getCalorieBurnt(any())).thenThrow(NoActivityMatchWithDatSetException.class);
//        mockMvc.perform(get("/api/v1/calorieBurnt")
//                .param("activityDetail", "30 min bungeejumping")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isConflict())
//                .andExpect(MockMvcResultMatchers.content().string("Sorry! your activity is not known by our data set."))
//                .andDo(print())
//                .andDo(document("handle-NoActivityMatchWithDatSetException"
//                        ,preprocessRequest(prettyPrint())
//                        ,preprocessResponse(prettyPrint())));
//    }
}


