package com.eliceteam8.edupay.receipt.controller;

import com.eliceteam8.edupay.receipt.dto.ReceiptDto;
import com.eliceteam8.edupay.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService){
        this.receiptService=receiptService;
    }

    @PostMapping
    public ResponseEntity<ReceiptDto> addReceipt(@RequestBody ReceiptDto receiptDto){
        receiptService.createReceipt(receiptDto);
        return ResponseEntity.ok(receiptDto);
    }
}
