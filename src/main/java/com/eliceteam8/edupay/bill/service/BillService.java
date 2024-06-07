package com.eliceteam8.edupay.bill.service;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.academy_management.repository.AcademyRepository;
import com.eliceteam8.edupay.academy_management.repository.AcademyStudentRepository;
import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.bill.domain.BillLog;
import com.eliceteam8.edupay.bill.domain.Status;
import com.eliceteam8.edupay.bill.dto.request.CreateMultipleBillsRequest;
import com.eliceteam8.edupay.bill.dto.response.BillInfoResponse;
import com.eliceteam8.edupay.bill.dto.request.CreateSingleBillRequest;
import com.eliceteam8.edupay.bill.dto.response.BillLogResponse;
import com.eliceteam8.edupay.bill.repository.BillLogRepository;
import com.eliceteam8.edupay.bill.repository.BillRepository;
import com.eliceteam8.edupay.global.exception.EntityNotFoundException;
import com.eliceteam8.edupay.global.exception.MessageTooLongException;
import com.eliceteam8.edupay.global.exception.NotEnoughRemainingBillsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillService {
    private static final int MAX_MESSAGE_LENGTH = 255;
    private static final String DEFAULT_MESSAGE = "Please pay the bill within the due date.";
    private static final long INITIAL_REMAINING_BILLS = 10L;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillLogRepository billLogRepository;

    @Autowired
    private AcademyStudentRepository academyStudentRepository;

    @Transactional
    public BillInfoResponse createBill(CreateSingleBillRequest request) {
        Optional<BillLog> optionalBillLog = billLogRepository.findFirstByOrderByCreatedAtDesc();
        BillLog billLog;

        if (optionalBillLog.isEmpty() || optionalBillLog.get().getRemainingBills() <= 0) {
//            throw new NotEnoughRemainingBillsException("No remaining bills", "NOT_ENOUGH_REMAINING_BILLS");
            billLog = new BillLog();
            billLog.setRemainingBills(INITIAL_REMAINING_BILLS);
            billLog.setCreatedAt(LocalDateTime.now());
            billLogRepository.save(billLog);
        } else {
            billLog = optionalBillLog.get();
        }

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

        billLog.setRemainingBills(billLog.getRemainingBills() - 1);
        billLogRepository.save(billLog);

        BillLog newBillLog = new BillLog();
        newBillLog.setBill(bill);
        newBillLog.setRemainingBills(billLog.getRemainingBills());
        newBillLog.setCreatedAt(LocalDateTime.now());
        billLogRepository.save(newBillLog);

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

    @Transactional
    public List<BillInfoResponse> createMultipleBills(CreateMultipleBillsRequest request) {
        Optional<BillLog> optionalBillLog = billLogRepository.findFirstByOrderByCreatedAtDesc();
        BillLog billLog;

        if (optionalBillLog.isEmpty() || optionalBillLog.get().getRemainingBills() < request.getStudentIds().size()) {
//            throw new NotEnoughRemainingBillsException("Not enough remaining bills", "NOT_ENOUGH_REMAINING_BILLS");
            billLog = new BillLog();
            billLog.setRemainingBills(INITIAL_REMAINING_BILLS);
            billLog.setCreatedAt(LocalDateTime.now());
            billLogRepository.save(billLog);
        } else {
            billLog = optionalBillLog.get();
        }

        String message = DEFAULT_MESSAGE;

        List<BillInfoResponse> responses = request.getStudentIds().stream()
                .map(studentId -> createBillForStudent(studentId, message))
                .collect(Collectors.toList());

        billLog.setRemainingBills(billLog.getRemainingBills() - request.getStudentIds().size());
        billLogRepository.save(billLog);

        return responses;
    }

    private BillInfoResponse createBillForStudent(Long studentId, String message) {
        AcademyStudent student = academyStudentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        Academy academy = student.getAcademy();
        if (academy == null) {
            throw new EntityNotFoundException("Academy not found for student id: " + studentId);
        }

        List<String> lectureNames = student.getLectures().stream()
                .map(Lecture::getLectureName)
                .collect(Collectors.toList());

        if (lectureNames.isEmpty()) {
            throw new EntityNotFoundException("No lectures found for student id: " + studentId);
        }

        long totalPrice = student.getLectures().stream()
                .mapToLong(Lecture::getPrice)
                .sum();

        LocalDateTime dueDate = LocalDateTime.now().plusWeeks(2);

        Bill bill = new Bill();
        bill.setDueDate(dueDate);
        bill.setMessage(message);
        bill.setStatus(Status.BEFORE);
        bill.setStudent(student);
        bill.setAcademy(academy);
        bill.setTotalPrice(totalPrice);
        billRepository.save(bill);

        BillLog newBillLog = new BillLog();
        newBillLog.setBill(bill);
        newBillLog.setRemainingBills(billLogRepository.findFirstByOrderByCreatedAtDesc().orElseThrow(() -> new IllegalStateException("No BillLog found")).getRemainingBills());
        newBillLog.setCreatedAt(LocalDateTime.now());
        billLogRepository.save(newBillLog);

        BillInfoResponse billInfoResponse = new BillInfoResponse();
        billInfoResponse.setAcademyName(academy.getAcademyName());
        billInfoResponse.setStudentName(student.getStudentName());
        billInfoResponse.setGrade(student.getGrade());
        billInfoResponse.setContact(student.getPhoneNumber());
        billInfoResponse.setLectureNames(lectureNames);
        billInfoResponse.setTotalPrice(totalPrice);
        billInfoResponse.setDueDate(dueDate);
        billInfoResponse.setMessage(message);

        return billInfoResponse;
    }

    public List<BillLogResponse> getBillLogs() {
        return billLogRepository.findAll().stream()
                .map(log -> {
                    BillLogResponse response = new BillLogResponse();
                    response.setId(log.getId());
                    response.setRemainingBills(log.getRemainingBills());
                    response.setCreatedAt(log.getCreatedAt());
                    response.setBillId(log.getBill() != null ? log.getBill().getId() : null);
                    return response;
                })
                .collect(Collectors.toList());
    }
}