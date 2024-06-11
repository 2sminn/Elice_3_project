package com.eliceteam8.edupay.receipt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptRequestDto {
    private Long studentId;
    private String year;
    private String month;
}
