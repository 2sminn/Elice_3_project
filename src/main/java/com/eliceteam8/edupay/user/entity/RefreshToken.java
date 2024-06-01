package com.eliceteam8.edupay.user.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
public class RefreshToken {

    private String refreshToken;
    private String email;


    public RefreshToken(String refreshToken, String email) {
        this.refreshToken = refreshToken;
        this.email = email;
    }
}
