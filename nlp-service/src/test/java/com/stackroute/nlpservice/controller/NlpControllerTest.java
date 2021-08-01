package com.stackroute.nlpservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.nlpservice.model.Nutrient;
import com.stackroute.nlpservice.service.NlpService;
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
@RunWith(SpringRunner.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs
public class NlpControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Mock
    private NlpService nlpService;
    List<Nutrient> nutrients;
    private String foodConsumed;
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    @Before
    public void setUp(){
        foodConsumed = "1 apple, 2 samosa and 2 glass water";
        nutrients = new ArrayList<>();
        nutrients.add(new Nutrient("apple", 182.0, 18.8, 4.3, 0.001, 0.02, 0.1, 0.3, 0.0, 0.5, 25.6, 96.4));
        nutrients.add(new Nutrient("samosa", 200.0, 3.2, 4.2, 0.853, 0.105, 14.2, 34.8, 0.055, 7.0, 48.4, 521.6));
        nutrients.add(new Nutrient("water", 474.0, 0.0, 0.0, 0.019, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(this.restDocumentation)).build();
    }

    @Test
    public void getCalorieBurntSuccess() throws Exception {
        when(nlpService.getIngredientInformationOfFoodConsumed(foodConsumed)).thenReturn(nutrients);
        mockMvc.perform(get("/api/v1/nutrition")
                .param("foodConsumed", foodConsumed))
                .andExpect(status().isOk()).andDo(print())
                .andDo(document("get-nutrition-details"
                        ,preprocessRequest(prettyPrint())
                        ,preprocessResponse(prettyPrint())));
    }
    public static String asJsonString(final Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
