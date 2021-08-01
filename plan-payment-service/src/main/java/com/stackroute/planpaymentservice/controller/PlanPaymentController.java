package com.stackroute.planpaymentservice.controller;

import com.stackroute.planpaymentservice.model.PaymentDetail;
import com.stackroute.planpaymentservice.model.PaymentResponse;
import com.stackroute.planpaymentservice.service.PlanPaymentService;
//import com.stackroute.planpaymentservice.service.RabbitMqSender;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/*
this class is for controller handling request that mapped for payment /charge to make payment
 */
@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin
public class PlanPaymentController {

    private PlanPaymentService planPaymentService;
//    private RabbitMqSender rabbitMqSender;

    @Autowired
    public PlanPaymentController(PlanPaymentService planPaymentService) {
        this.planPaymentService = planPaymentService;
//        this.rabbitMqSender = rabbitMqSender;
    }

    /*handler for making payment from  credit card card get details of card from request body pass payment details to
    service class  generate paymentResponse that sent back with httpStatus.CREATED;
     */
    @PostMapping("/charge")
    public ResponseEntity<?> chargePayment(@RequestBody PaymentDetail paymentDetail) throws StripeException {
        PaymentResponse paymentResponse = planPaymentService.createTransaction(paymentDetail);
        System.out.println("testing");
//        rabbitMqSender.send(paymentResponse);
        return new ResponseEntity<PaymentResponse>(paymentResponse, HttpStatus.CREATED);
    }

    @GetMapping("/message")
    public String test() {
        return "Hello JavaInUse Called in payment Service";
    }
    /*
    Handle Stripe Exception and return error message as response with httpStatus.CONFLICT
     */
    @ExceptionHandler(value = StripeException.class)
    public ResponseEntity<?> handleException(StripeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

}
