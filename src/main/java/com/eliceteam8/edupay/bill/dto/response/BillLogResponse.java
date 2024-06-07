package com.eliceteam8.edupay.bill.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class BillLogResponse {
    private Long id;
    private Long remainingBills;
    private LocalDateTime createdAt;
    private Long billId;
}
