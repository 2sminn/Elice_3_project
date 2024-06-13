package com.eliceteam8.edupay.point.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefundDTO {

    private boolean success;

    private String message;
}
