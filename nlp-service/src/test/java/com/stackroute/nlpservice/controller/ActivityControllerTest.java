package com.stackroute.nlpservice.controller;

import com.stackroute.nlpservice.exception.NoActivityMatchWithDatSetException;
import com.stackroute.nlpservice.exception.NoTimeGivenException;
import com.stackroute.nlpservice.exception.NoTimeUnitGivenException;
import com.stackroute.nlpservice.model.Activity;
import com.stackroute.nlpservice.model.Nutrient;
import com.stackroute.nlpservice.service.ActivityService;
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
public class ActivityControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @Mock
    private ActivityService activityService;
    List<Activity> activities;
    String activityDetail;
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    @Before
    public void setUp(){
        activityDetail = "1 hour yoga and 25 min dancing";
        activities = new ArrayList<>();
        activities.add(new Activity(1.0, "hour", "yoga", 360.0));
        activities.add(new Activity(25.0, "min", "dancing", 100.0));
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(this.restDocumentation)).build();
    }

//    @Test
//    public void getCalorieBurntSuccess() throws Exception {
//        when(activityService.getCalorieBurnt(activityDetail)).thenReturn(activities);
//        mockMvc.perform(get("/api/v1/calorieBurnt")
//                .param("activityDetail", activityDetail))
//                .andExpect(status().isOk()).andDo(print())
//                .andDo(document("get-calorieBurnt-details"
//                        ,preprocessRequest(prettyPrint())
//                        ,preprocessResponse(prettyPrint())));
//    }

    @Test
    public void getCalorieBurntTimeNotGetFailure() throws Exception {
        when(activityService.getCalorieBurnt(any())).thenThrow(NoTimeGivenException.class);
        mockMvc.perform(get("/api/v1/calorieBurnt")
                .param("activityDetail", "min dancing")
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("Please give activity time."))
                .andDo(print())
                .andDo(document("handle-NoTimeGivenException"
                        ,preprocessRequest(prettyPrint())
                        ,preprocessResponse(prettyPrint())));
    }
    @Test
    public void getCalorieBurntTimeUnitNotGetFailure() throws Exception {
        when(activityService.getCalorieBurnt(any())).thenThrow(NoTimeUnitGivenException.class);
        mockMvc.perform(get("/api/v1/calorieBurnt")
                .param("activityDetail", "1 yoga")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("Please give proper activity time unit."))
                .andDo(print())
                .andDo(document("handle-NoTimeUnitGivenException"
                        ,preprocessRequest(prettyPrint())
                        ,preprocessResponse(prettyPrint())));
    }
    @Test
    public void getCalorieBurntNoActivityMatchFromDataSetFailure() throws Exception {
        when(activityService.getCalorieBurnt(any())).thenThrow(NoActivityMatchWithDatSetException.class);
        mockMvc.perform(get("/api/v1/calorieBurnt")
                .param("activityDetail", "30 min bungeejumping")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("Sorry! your activity is not known by our data set."))
                .andDo(print())
                .andDo(document("handle-NoActivityMatchWithDatSetException"
                        ,preprocessRequest(prettyPrint())
                        ,preprocessResponse(prettyPrint())));
    }
}

