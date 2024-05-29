package com.eliceteam8.edupay.user.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpDTO {


    @Email @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 4, max = 40)
    private String password;

    @NotEmpty
    private String username;

    @Pattern(regexp = "^010-(\\d{3}|\\d{4})-(\\d{4})$")
    private String phoneNumber;

    @NotEmpty
    private String academyName;

    @Email
    private String academyEmail;

    @NotNull
    private int postCode;

    @NotEmpty
    private String address;

    @NotEmpty
    private String addressDetail;

    @NotEmpty
    private String landlineNumber;
}
