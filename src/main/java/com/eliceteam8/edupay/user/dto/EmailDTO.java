package com.eliceteam8.edupay.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EmailDTO {

    private Boolean result;
    private String message;
}
