package com.stackroute.socketnotificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SocketNotification {
    private String fromEmail;
    private String toEMail;
    private String message;
}
