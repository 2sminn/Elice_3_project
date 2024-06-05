package com.eliceteam8.edupay.bill.service;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.academy_management.repository.AcademyRepository;
import com.eliceteam8.edupay.academy_management.repository.AcademyStudentRepository;
import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.bill.domain.Status;
import com.eliceteam8.edupay.bill.dto.request.BillInfoResponse;
import com.eliceteam8.edupay.bill.dto.request.CreateSingleBillRequest;
import com.eliceteam8.edupay.bill.repository.BillRepository;
import com.eliceteam8.edupay.global.exception.EntityNotFoundException;
import com.eliceteam8.edupay.global.exception.MessageTooLongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {
    private static final int MAX_MESSAGE_LENGTH = 255;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private AcademyStudentRepository academyStudentRepository;

    @Transactional
    public BillInfoResponse createBill(CreateSingleBillRequest request) {
        if (request.getMessage().length() > MAX_MESSAGE_LENGTH) {
            throw new MessageTooLongException("Message exceeds maximum allowed length", "MESSAGE_TOO_LONG");
        }

        AcademyStudent student = academyStudentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + request.getStudentId()));

        Academy academy = student.getAcademy();
        if (academy == null) {
            throw new EntityNotFoundException("Academy not found for student id: " + request.getStudentId());
        }

        List<String> lectureNames = student.getLectures().stream()
                .map(Lecture::getLectureName)
                .collect(Collectors.toList());

        if (lectureNames.isEmpty()) {
            throw new EntityNotFoundException("No lectures found for student id: " + request.getStudentId());
        }

        long totalPrice = student.getLectures().stream()
                .mapToLong(Lecture::getPrice)
                .sum();

        LocalDateTime dueDate = LocalDateTime.now().plusWeeks(2);

        Bill bill = new Bill();
        bill.setDueDate(dueDate);
        bill.setMessage(request.getMessage());
        bill.setStatus(Status.BEFORE);
        bill.setStudent(student);
        bill.setAcademy(academy);
        bill.setTotalPrice(totalPrice);
        billRepository.save(bill);

        BillInfoResponse billInfoResponse = new BillInfoResponse();
        billInfoResponse.setAcademyName(academy.getAcademyName());
        billInfoResponse.setStudentName(student.getStudentName());
        billInfoResponse.setGrade(student.getGrade());
        billInfoResponse.setContact(student.getPhoneNumber());
        billInfoResponse.setLectureNames(lectureNames);
        billInfoResponse.setTotalPrice(totalPrice);
        billInfoResponse.setDueDate(dueDate);
        billInfoResponse.setMessage(request.getMessage());

        return billInfoResponse;
    }
}