package com.eliceteam8.edupay.bill.controller;

import com.eliceteam8.edupay.bill.dto.request.CreateBillRequest;
import com.eliceteam8.edupay.bill.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/bill/create")
    public void createBill(@RequestBody CreateBillRequest request) {
        billService.createBill(request);
    }
}
