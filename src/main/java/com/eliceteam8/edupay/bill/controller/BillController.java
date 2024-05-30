package com.eliceteam8.edupay.bill.controller;

import com.eliceteam8.edupay.bill.dto.request.CreateBillRequest;
import com.eliceteam8.edupay.bill.dto.response.GetBillResponse;
import com.eliceteam8.edupay.bill.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/create")
    public void createBill(@RequestBody CreateBillRequest request) {
        billService.createBill(request);
    }

    @GetMapping("")
    public GetBillResponse getBill(@RequestParam long id) {
        return billService.getBill(id);
    }
}
