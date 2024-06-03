package com.eliceteam8.edupay.receipt.service;

import com.eliceteam8.edupay.receipt.dto.ReceiptDto;
import com.eliceteam8.edupay.receipt.entity.ReceiptEntity;
import com.eliceteam8.edupay.receipt.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {
    private ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository){
        this.receiptRepository=receiptRepository;
    }

    public void createReceipt(ReceiptDto receiptDto){
        ReceiptEntity receiptEntity = ReceiptDto.toEntity(receiptDto);
        receiptRepository.save(receiptEntity);
    }
    // TODO:orderId를통해 학생이름,과목이름,과목정보 등 가져오기
//    public List<ReceiptDto> getReceiptsByInfo(String studentId,String year,String month){
//
//    }
}
