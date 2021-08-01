package com.stackroute.planpaymentservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.planpaymentservice.model.PaymentDetail;
import com.stackroute.planpaymentservice.model.PaymentResponse;
import com.stackroute.planpaymentservice.service.PlanPaymentService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs
public class PlanPaymentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @Mock
    private PlanPaymentService planPaymentService;

    ObjectMapper objectMapper = new ObjectMapper();
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(documentationConfiguration(this.restDocumentation)).build();
    }

    @Test
    public void addPaymentDetailsGoldThenReturnPaymentResponse() throws Exception {
        PaymentDetail paymentDetail = new PaymentDetail("Vtushid@gmail.com", "4242424242424242"
                , "12", "25", "123", "GOLD");
        PaymentResponse paymentResponse = new PaymentResponse("Vtushid@gmail.com",true,"GOLD","ch_1J3gfOSFEefsUarr9QaSa08r");
        String jsonRequest = objectMapper.writeValueAsString(paymentDetail);
        String jsonResponse = objectMapper.writeValueAsString(paymentResponse);
        when(planPaymentService.createTransaction(any())).thenReturn(paymentResponse);
        mockMvc.perform(post("/api/v1/payment/charge")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("created-transaction"
                        ,preprocessRequest(prettyPrint())
                        ,preprocessResponse(prettyPrint())));

    }

    @Test
    public void addPaymentDetailsPlatinumThenReturnPaymentResponse() throws Exception {
        PaymentDetail paymentDetail = new PaymentDetail("Vtushid@gmail.com", "4242424242424242"
                , "12", "25", "123", "Platinum");
        String jsonRequest = objectMapper.writeValueAsString(paymentDetail);
        mockMvc.perform(post("/api/v1/payment/charge")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

    }

    @Test
    public void whenAddWrongExpYearShouldThrowException() throws Exception {
        PaymentDetail paymentDetail = new PaymentDetail("Vtushid@gmail.com", "1233456644534"
                , "12", "20", "123", "GOLD");
        String jsonRequest = objectMapper.writeValueAsString(paymentDetail);
        mockMvc.perform(post("/api/v1/payment/charge")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isConflict());
    }

    @Test
    public void whenAddWrongExpMonthShouldThrowException() throws Exception {
        PaymentDetail paymentDetail = new PaymentDetail("Vtushid@gmail.com", "4242424242424242"
                , "120", "25", "123", "GOLD");
        String jsonRequest = objectMapper.writeValueAsString(paymentDetail);
        mockMvc.perform(post("/api/v1/payment/charge")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isConflict());
    }



    @Test
    public void whenAddWrongCvcNumberShouldThrowException() throws Exception {
        PaymentDetail paymentDetail = new PaymentDetail("Vtushid@gmail.com", "4242424242424242"
                , "12", "25", "123123456", "GOLD");
        String jsonRequest = objectMapper.writeValueAsString(paymentDetail);
        mockMvc.perform(post("/api/v1/payment/charge")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isConflict());
    }
}