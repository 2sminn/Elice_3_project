package com.eliceteam8.edupay.receipt.controller;

import com.eliceteam8.edupay.receipt.dto.ReceiptDto;
import com.eliceteam8.edupay.receipt.dto.ReceiptRequestDto;
import com.eliceteam8.edupay.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService){
        this.receiptService=receiptService;
    }

    @GetMapping
    public ResponseEntity<List<ReceiptDto>> getReceiptsByInfo(@RequestBody ReceiptRequestDto request) {
        List<ReceiptDto> receipts = receiptService.getReceiptsByInfo(request.getStudentId(), request.getYear(), request.getMonth());
        return ResponseEntity.ok(receipts);
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendReceiptByEmail(@RequestBody ReceiptRequestDto request) {
        receiptService.sendReceiptByEmail(request.getStudentId(), request.getYear(), request.getMonth());
        return ResponseEntity.ok("영수증을 이메일로 발송했습니다.");
    }
}
