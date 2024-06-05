package com.eliceteam8.edupay.bill.controller;

import com.eliceteam8.edupay.bill.dto.request.BillInfo;
import com.eliceteam8.edupay.bill.dto.request.CreateBillRequest;
import com.eliceteam8.edupay.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    // 학생 이름으로 청구서 생성 및 반환 API
    @PostMapping("")
    public BillInfo createBill(@RequestBody CreateBillRequest request) {
        return billService.createBill(request);
    }
}
