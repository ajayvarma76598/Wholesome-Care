package com.stackroute.planpaymentservice.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
/*
this class is a model class for response that sent back by post mapping
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "@id",scope = PaymentResponse.class)
public class PaymentResponse implements Serializable {
    private String emailId;
    private boolean activeStatus;
    private String plan;
    private String chargeId;

    public PaymentResponse(String emailId, boolean activeStatus, String plan) {
        this.emailId = emailId;
        this.activeStatus = activeStatus;
        this.plan = plan;
    }
}
