package com.eliceteam8.edupay.receipt.controller;

import com.eliceteam8.edupay.receipt.dto.ReceiptDto;
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

    @PostMapping
    public ResponseEntity<ReceiptDto> addReceipt(@RequestBody ReceiptDto receiptDto){
        receiptService.createReceipt(receiptDto);
        return ResponseEntity.ok(receiptDto);
    }

    @GetMapping
    public ResponseEntity<List<ReceiptDto>> getReceiptsByInfo(@RequestParam Long studentId, @RequestParam String year, @RequestParam String month) {
        List<ReceiptDto> receipts = receiptService.getReceiptsByInfo(studentId, year, month);
        return ResponseEntity.ok(receipts);
    }
    @PostMapping("/send")
    public ResponseEntity<String> sendReceiptByEmail(@RequestParam Long studentId, @RequestParam String year, @RequestParam String month) {
        receiptService.sendReceiptByEmail(studentId, year, month);
        return ResponseEntity.ok("영수증을 이메일로 발송했습니다.");
    }
}
