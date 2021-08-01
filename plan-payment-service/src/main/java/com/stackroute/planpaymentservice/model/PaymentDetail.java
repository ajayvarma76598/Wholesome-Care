package com.stackroute.planpaymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
this class is a model class to get data from httprequest body for making payments
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDetail {

    private String emailId;
    private String card_number;
    private String exp_month;
    private String exp_year;
    private String cvc;
    private String plan;
}
