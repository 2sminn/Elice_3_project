package com.eliceteam8.edupay.bill.service;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.academy_management.repository.AcademyRepository;
import com.eliceteam8.edupay.academy_management.repository.AcademyStudentRepository;
import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.bill.domain.Status;
import com.eliceteam8.edupay.bill.dto.request.BillInfo;
import com.eliceteam8.edupay.bill.dto.request.CreateBillRequest;
import com.eliceteam8.edupay.bill.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private AcademyStudentRepository academyStudentRepository;

    @Transactional
    public BillInfo createBill(CreateBillRequest request) {
        AcademyStudent student = academyStudentRepository.findByStudentName(request.getStudentName())
                .orElseThrow(() -> new IllegalArgumentException("Student not found with name: " + request.getStudentName()));

        Academy academy = student.getAcademy();
        List<String> lectureNames = student.getLectures().stream()
                .map(Lecture::getLectureName)
                .collect(Collectors.toList());

        long totalPrice = student.getLectures().stream()
                .mapToLong(Lecture::getPrice)
                .sum();

        Bill bill = new Bill();
        bill.setDueDate(request.getDueDate());
        bill.setMessage(request.getMessage());
        bill.setStatus(Status.BEFORE);
        bill.setStudent(student);
        bill.setAcademy(academy);
        bill.setTotalPrice(totalPrice);
        billRepository.save(bill);

        BillInfo billInfo = new BillInfo();
        billInfo.setAcademyName(academy.getAcademyName());
        billInfo.setStudentName(student.getStudentName());
        billInfo.setGrade(student.getGrade());
        billInfo.setContact(student.getPhoneNumber());
        billInfo.setLectureNames(lectureNames);
        billInfo.setTotalPrice(totalPrice);
        billInfo.setDueDate(request.getDueDate());
        billInfo.setMessage(request.getMessage());

        return billInfo;
    }
}