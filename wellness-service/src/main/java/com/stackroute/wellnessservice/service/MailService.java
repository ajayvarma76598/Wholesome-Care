package com.stackroute.wellnessservice.service;

public interface MailService {
    boolean sendEmail(String to, String subject, String message,String cityName) throws Exception;
    /*void sendEmail(GuestUser guestUser) throws MailException;*/
}
