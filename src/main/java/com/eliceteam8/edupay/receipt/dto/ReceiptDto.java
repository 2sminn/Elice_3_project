package com.eliceteam8.edupay.receipt.dto;

import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.receipt.entity.ReceiptEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDto {

    private Long receiptId;
    private Long billId;
    private LocalDateTime receiptDate;
    private Long studentId;
    private String studentName;
    private String birthdate;
    private List<Lecture> lectures;
    private Long totalPrice;
    private String grade;

}
