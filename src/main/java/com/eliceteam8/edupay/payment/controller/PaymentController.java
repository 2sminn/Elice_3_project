package com.eliceteam8.edupay.payment.controller;

import com.eliceteam8.edupay.payment.dto.CallbackRequestDTO;
import com.eliceteam8.edupay.payment.dto.PaymentInfoDTO;
import com.eliceteam8.edupay.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{billId}")
    public PaymentInfoDTO paymentPage(@PathVariable(name = "billId", required = false) Long billId) {
        PaymentInfoDTO paymentInfoDTO = paymentService.createPaymentInfoDTO(billId);

        return paymentInfoDTO;
    }

    @PostMapping("")
    public ResponseEntity<Void> validationPayment(@RequestBody CallbackRequestDTO request) throws IOException, IamportResponseException {
        paymentService.validatePayment(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
