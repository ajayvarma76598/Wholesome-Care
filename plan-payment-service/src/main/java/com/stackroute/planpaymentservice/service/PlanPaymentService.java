package com.stackroute.planpaymentservice.service;

import com.stackroute.planpaymentservice.model.PaymentDetail;
import com.stackroute.planpaymentservice.model.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlanPaymentService {

    @Value("stripe.api.key")
    private String API_KEY="sk_test_51J29UwSFEefsUarro0f2y71eVmzZW9jpny6IuQPRFpvyRbH1IdLJ4ytvttDvS6SXHAY05Lga8YAoxHqXeUUtFB8M00PXg8akBx" ;

    @Value("#{new Integer('${payment.amount.gold}')}")
    private Integer amountGold ;

    @Value("#{new Integer('${payment.amount.plain}')}")
    private Integer amountPlatinum;

    @Value("${payment.cardnumber}")
    private String cardNumber ;

    // setting api key for Stripe payment gateway . this api key is always goes with each api calls
    public PlanPaymentService( ) {

        Stripe.apiKey = API_KEY;
    }

    // create card and return a token object for that card
    public Token createCard(String number, String exp_month, String exp_year, String cvc) throws StripeException {
        Map<String, Object> cardParams = new HashMap<>();
        Map<String, Object> tokenParams = new HashMap<>();
        cardParams.put("number", number);
        cardParams.put("exp_month", exp_month);
        cardParams.put("exp_year", exp_year);
        cardParams.put("cvc", cvc);
        tokenParams.put("card", cardParams);
        return Token.create(tokenParams);
    }

    // charge money to user
    public Charge charge(String token, int amount) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "inr");
        chargeParams.put("source", token);
        return Charge.create(chargeParams);
    }

    // create whole transaction for each plan
    public PaymentResponse createTransaction(PaymentDetail paymentDetail) throws StripeException {

        Token token = createCard(paymentDetail.getCard_number(), paymentDetail.getExp_month(), paymentDetail.getExp_year(), paymentDetail.getCvc());
        Charge charge = null;
        PaymentResponse paymentResponse;
        // make charge transaction from card to company account
        if (paymentDetail.getPlan().equalsIgnoreCase("GOLD")) {
            charge = charge(token.getId(), amountGold);
        } else if (paymentDetail.getPlan().equalsIgnoreCase("PLATINUM")) {
            charge = charge(token.getId(), amountPlatinum);
        }

        // generate response according to transaction success or not


        if (charge.getStatus().equalsIgnoreCase("succeeded")) {
            paymentResponse = new PaymentResponse(paymentDetail.getEmailId()
                    , true
                    , paymentDetail.getPlan()
                    , charge.getId());
        } else {
            paymentResponse = new PaymentResponse(paymentDetail.getEmailId()
                    , false
                    , paymentDetail.getPlan()
                    , charge.getId());
        }

        return paymentResponse;
    }
}
