package com.eliceteam8.edupay.bill.controller;

import com.eliceteam8.edupay.bill.dto.request.CreateMultipleBillsRequest;
import com.eliceteam8.edupay.bill.dto.response.BillDetailResponse;
import com.eliceteam8.edupay.bill.dto.response.BillInfoResponse;
import com.eliceteam8.edupay.bill.dto.request.CreateSingleBillRequest;
import com.eliceteam8.edupay.bill.dto.response.BillLogResponse;
import com.eliceteam8.edupay.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    // 학생 ID로 단일 청구서 생성 및 반환 API
    @PostMapping("")
    public BillInfoResponse createBill(@RequestBody CreateSingleBillRequest request) {
        return billService.createBill(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDetailResponse> getBillInfo(@PathVariable Long id) {
        BillDetailResponse response = billService.getBillDetail(id);
        return ResponseEntity.ok(response);
    }

    // 다중 청구서 생성 및 반환 API
    @PostMapping("/multiple")
    public List<BillInfoResponse> createMultipleBills(@RequestBody CreateMultipleBillsRequest request) {
        return billService.createMultipleBills(request);
    }

    // 청구서 발송 내역 확인 API
    @GetMapping("/logs")
    public Page<BillLogResponse> getBillLogs(Pageable pageable) {
        return billService.getBillLogs(pageable);
    }
}
