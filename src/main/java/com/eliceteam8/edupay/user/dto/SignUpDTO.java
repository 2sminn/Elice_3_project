package com.eliceteam8.edupay.user.dto;

import com.eliceteam8.edupay.global.enums.ErrorMessage;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpDTO {

    @Email(message = ErrorMessage.INVALID_EMAIL)
    @NotBlank
    private String email;

    @NotEmpty
    @Size(min = 4, max = 40)
    private String password;

    @NotBlank
    @Size(min = 2, max = 20)
    private String username;

    @Pattern(regexp = "^010-(\\d{3}|\\d{4})-(\\d{4})$",message = ErrorMessage.INVALID_PHONE_NUMBER)
    private String phoneNumber;

    @NotBlank
    private String academyName;

    @NotBlank
    private String businessNumber;

    @Email(message = ErrorMessage.INVALID_EMAIL)
    private String academyEmail;

    @NotNull
    private int zipCode;

    @NotBlank
    private String address;

    @NotBlank
    private String addressDetail;

    @NotEmpty
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",message = ErrorMessage.INVALID_PHONE_NUMBER)
    private String landlineNumber;
}
