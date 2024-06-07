package com.eliceteam8.edupay.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInfoDTO {

    private Long billId;

    private String billName;

    private Long amount;
}
