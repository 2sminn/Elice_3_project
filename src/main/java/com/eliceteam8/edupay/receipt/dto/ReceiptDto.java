package com.eliceteam8.edupay.receipt.dto;

import com.eliceteam8.edupay.receipt.entity.ReceiptEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDto {

    private Long receiptId;
    private Long orderId;
    private LocalDateTime receiptDate;
    private String studentName;
    private LocalDate birthdate;
    private String lectureName;
    private int totalPrice;

    public static ReceiptEntity toEntity(ReceiptDto receiptDto){
        return ReceiptEntity.builder()
                .receiptId(receiptDto.getReceiptId())
                .orderId(receiptDto.getOrderId())
                .receiptDate(receiptDto.getReceiptDate())
                .build();
    }

    public static ReceiptDto fromEntity(ReceiptEntity receiptEntity,String studentName,LocalDate birthdate
    ,String lectureName,int totalPrice){
        return ReceiptDto.builder()
                .receiptId(receiptEntity.getReceiptId())
                .orderId(receiptEntity.getOrderId())
                .receiptDate(receiptEntity.getReceiptDate())
                .studentName(studentName)
                .birthdate(birthdate)
                .lectureName(lectureName)
                .totalPrice(totalPrice)
                .build();
    }

}
