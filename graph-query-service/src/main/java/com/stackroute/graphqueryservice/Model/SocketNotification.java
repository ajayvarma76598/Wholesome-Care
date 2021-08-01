package com.stackroute.graphqueryservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SocketNotification {
    private String fromEmail;
    private String toEMail;
    private String message;
}
