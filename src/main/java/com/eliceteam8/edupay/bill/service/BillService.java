package com.eliceteam8.edupay.bill.service;

import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.bill.dto.request.CreateBillRequest;
import com.eliceteam8.edupay.bill.dto.response.GetBillResponse;
import com.eliceteam8.edupay.bill.repository.BillRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final ModelMapper modelMapper;

    public BillService(BillRepository billRepository, ModelMapper modelMapper) {
        this.billRepository = billRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void createBill(CreateBillRequest request) {
        Bill bill = new Bill(request.getDueDate(), request.getMessage());
        bill.setStatusToCompleted();
        billRepository.save(bill);
    }

    @Transactional(readOnly = true)
    public GetBillResponse getBill(long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill not found"));

        GetBillResponse response = new GetBillResponse();
        response.setId(bill.getId());
        response.setTotalPrice(bill.getTotalPrice());
        //response.setLectureDetails(bill.getLectureDetails());
        response.setDueDate(bill.getDueDate());
        response.setCreatedAt(bill.getCreatedAt());
        response.setUpdatedAt(bill.getUpdatedAt());
        response.setStatus(bill.getStatus().name());
        response.setMessage(bill.getMessage());
        response.setAcademyId(bill.getAcademy().getId());
        response.setStudentId(bill.getStudentId());

        return response;
    }
}
