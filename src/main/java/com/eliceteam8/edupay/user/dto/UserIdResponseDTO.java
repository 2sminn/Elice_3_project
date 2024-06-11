package com.eliceteam8.edupay.user.dto;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserIdResponseDTO {

    private String result;
    private Long userId;
}
