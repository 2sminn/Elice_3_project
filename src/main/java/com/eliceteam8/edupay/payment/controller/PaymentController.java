package com.eliceteam8.edupay.payment.controller;

import com.eliceteam8.edupay.payment.dto.CallbackRequestDTO;
import com.eliceteam8.edupay.payment.dto.PaymentInfoDTO;
import com.eliceteam8.edupay.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/payment/{billId}")
    public String paymentPage(@PathVariable(name = "billId", required = false) Long billId, Model model) {
        PaymentInfoDTO paymentInfoDTO = paymentService.createPaymentInfoDTO(billId);
        model.addAttribute("requestDTO", paymentInfoDTO);

        return "payment";
    }

    @ResponseBody
    @PostMapping("/payment")
    public ResponseEntity<IamportResponse<Payment>> validationPayment(@RequestBody String request) throws JsonProcessingException {
        CallbackRequestDTO callbackRequestDTO = CallbackRequestDTO.fromString(request);
        IamportResponse<Payment> iamportResponse = paymentService.validatePayment(callbackRequestDTO);

        return new ResponseEntity<>(iamportResponse, HttpStatus.OK);
    }

    @GetMapping("/success-payment")
    public String successPaymentPage() {
        return "success-payment";
    }

    @GetMapping("/fail-payment")
    public String failPaymentPage() {
        return "fail-payment";
    }
}
