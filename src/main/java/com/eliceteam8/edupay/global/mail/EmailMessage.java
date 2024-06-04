package com.eliceteam8.edupay.global.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailMessage {

    private String to;
    private String subject;
    private String message;
    private String url;
    private String uuid;
}
