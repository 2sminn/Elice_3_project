package com.eliceteam8.edupay.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class BooleanResultDTO {

    private Boolean result;
    private String message;
}
