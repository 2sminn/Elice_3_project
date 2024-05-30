package com.eliceteam8.edupay.payment.controller;

import com.eliceteam8.edupay.payment.dto.PaymentInfoDTO;
import com.eliceteam8.edupay.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
