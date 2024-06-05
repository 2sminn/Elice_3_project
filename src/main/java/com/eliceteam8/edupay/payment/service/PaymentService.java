package com.eliceteam8.edupay.payment.service;

import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.bill.domain.Status;
import com.eliceteam8.edupay.bill.repository.BillRepository;
import com.eliceteam8.edupay.payment.dto.CallbackRequestDTO;
import com.eliceteam8.edupay.payment.dto.PaymentInfoDTO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final BillRepository billRepository;
    private final IamportClient iamportClient;

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

    public IamportResponse<Payment> validatePayment(CallbackRequestDTO callbackRequestDTO) {
        try {
            IamportResponse<Payment> iamportResponse = getIamportResponse(callbackRequestDTO);
            Bill bill = billRepository.findByBillId(callbackRequestDTO.getBillId()).orElseThrow(NoSuchElementException::new);
            validatePaymentStatusAndPay(iamportResponse, bill);

            if (!Status.PAID.equals(bill.getStatus())) {
                bill.setStatusToPaid(iamportResponse.getResponse().getImpUid());
            }

            return iamportResponse;

        } catch (IamportResponseException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private IamportResponse<Payment> getIamportResponse(CallbackRequestDTO request) throws IamportResponseException, IOException {
        IamportResponse<Payment> iamportResponse = iamportClient.paymentByImpUid(request.getImpUid());

        return iamportResponse;
    }

    private void validatePaymentStatusAndPay(IamportResponse<Payment> iamportResponse, Bill bill) throws IamportResponseException, IOException {
        if (!iamportResponse.getResponse().getStatus().equals("paid")) {
            billRepository.delete(bill);
            throw new RuntimeException("결제 미완료");
        }

        Long totalPrice = bill.getTotalPrice();
        Long portOnePrice = iamportResponse.getResponse().getAmount().longValue();

        if (!Objects.equals(portOnePrice, totalPrice)) {
            throw new RuntimeException("결제금액 불일치");
        }
    }
}
