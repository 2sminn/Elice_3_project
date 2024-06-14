package com.eliceteam8.edupay.bill.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillDetailResponse {
    private String academyName;
    private String reason;
    private long totalPrice;
}
