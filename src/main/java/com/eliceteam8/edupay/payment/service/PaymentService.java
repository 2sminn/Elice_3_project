package com.eliceteam8.edupay.payment.service;

import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.bill.repository.BillRepository;
import com.eliceteam8.edupay.payment.dto.PaymentInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final BillRepository billRepository;

    public PaymentInfoDTO createPaymentInfoDTO(Long billId) {
        Bill bill = billRepository.findByBillId(billId)
                .orElseThrow(NoSuchElementException::new);

        PaymentInfoDTO paymentInfoDTO = PaymentInfoDTO.builder()
                .billId(bill.getId())
                .billName(bill.getAcademy().getAcademyName())
                .amount(bill.getTotalPrice())
                .build();

        return paymentInfoDTO;
    }
}
