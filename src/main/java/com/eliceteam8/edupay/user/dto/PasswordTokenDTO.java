package com.eliceteam8.edupay.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordTokenDTO {

    private String token;

    @NotBlank(message = "이메일이 값이 존재하지 않습니다.")
    private String email;

    @NotBlank(message = "유저네임 값이 존재하지 않습니다.")
    private String username;

}
