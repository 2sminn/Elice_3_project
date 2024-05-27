package com.eliceteam8.edupay.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpDTO {


    private String loginId;
    private String password;
    private String username;
    private String email;
    private String phoneNumber;
    private String academyName;
    private int postCode;
    private String address;
    private String landlineNumber;
}
