package com.eliceteam8.edupay.bill.service;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.academy_management.repository.AcademyStudentRepository;
import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.bill.domain.BillLog;
import com.eliceteam8.edupay.bill.domain.Status;
import com.eliceteam8.edupay.bill.dto.request.CreateMultipleBillsRequest;
import com.eliceteam8.edupay.bill.dto.request.CreateSingleBillRequest;
import com.eliceteam8.edupay.bill.dto.response.BillInfoResponse;
import com.eliceteam8.edupay.bill.dto.response.BillLogResponse;
import com.eliceteam8.edupay.bill.repository.BillLogRepository;
import com.eliceteam8.edupay.bill.repository.BillRepository;
import com.eliceteam8.edupay.global.exception.EntityNotFoundException;
import com.eliceteam8.edupay.global.exception.MessageTooLongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillService {
    private static final int MAX_MESSAGE_LENGTH = 255;
    private static final String DEFAULT_MESSAGE = "청구서를 기한 내에 납부해 주세요.";
    private static final long INITIAL_REMAINING_BILLS = 10L;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillLogRepository billLogRepository;

    @Autowired
    private AcademyStudentRepository academyStudentRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public BillInfoResponse createBill(CreateSingleBillRequest request) {
        Optional<BillLog> optionalBillLog = billLogRepository.findFirstByOrderByCreatedAtDesc();
        BillLog billLog;

        if (optionalBillLog.isEmpty() || optionalBillLog.get().getRemainingBills() <= 0) {
            billLog = new BillLog();
            billLog.setRemainingBills(INITIAL_REMAINING_BILLS);
            billLog.setCreatedAt(LocalDateTime.now());
            billLogRepository.save(billLog);
        } else {
            billLog = optionalBillLog.get();
        }

        if (request.getMessage().length() > MAX_MESSAGE_LENGTH) {
            throw new MessageTooLongException("메시지가 허용된 최대 길이를 초과했습니다.", "MESSAGE_TOO_LONG");
        }

        AcademyStudent student = academyStudentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + request.getStudentId() + "인 학생을 찾을 수 없습니다."));

        Academy academy = student.getAcademy();
        if (academy == null) {
            throw new EntityNotFoundException("ID가 " + request.getStudentId() + "인 학생의 학원을 찾을 수 없습니다.");
        }

        List<String> lectureNames = student.getLectures().stream()
                .map(Lecture::getLectureName)
                .collect(Collectors.toList());

        if (lectureNames.isEmpty()) {
            throw new EntityNotFoundException("ID가 " + request.getStudentId() + "인 학생의 강의를 찾을 수 없습니다.");
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

        // 이메일 보내기
        String subject = "청구서 안내";
        String text = "청구서가 생성되었습니다. 결제 URL: http://34.47.70.191/bill/" + bill.getId();
        emailService.sendSimpleMessage(student.getEmail(), subject, text);

        // 상태 변경
        bill.setStatusToCompleted();
        billRepository.save(bill);

        return billInfoResponse;
    }

    @Transactional
    public List<BillInfoResponse> createMultipleBills(CreateMultipleBillsRequest request) {
        Optional<BillLog> optionalBillLog = billLogRepository.findFirstByOrderByCreatedAtDesc();
        BillLog billLog;

        if (optionalBillLog.isEmpty() || optionalBillLog.get().getRemainingBills() < request.getStudentIds().size()) {
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
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + studentId + "인 학생을 찾을 수 없습니다."));

        Academy academy = student.getAcademy();
        if (academy == null) {
            throw new EntityNotFoundException("ID가 " + studentId + "인 학생의 학원을 찾을 수 없습니다.");
        }

        List<String> lectureNames = student.getLectures().stream()
                .map(Lecture::getLectureName)
                .collect(Collectors.toList());

        if (lectureNames.isEmpty()) {
            throw new EntityNotFoundException("ID가 " + studentId + "인 학생의 강의를 찾을 수 없습니다.");
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

        // 이메일 보내기
        String subject = "청구서 안내";
        String text = "청구서가 생성되었습니다. 결제 URL: http://34.47.70.191/bill/" + bill.getId();
        emailService.sendSimpleMessage(student.getEmail(), subject, text);

        // 상태 변경
        bill.setStatusToCompleted();
        billRepository.save(bill);

        return billInfoResponse;
    }

    public Page<BillLogResponse> getBillLogs(Pageable pageable) {
        Page<BillLog> billLogs = billLogRepository.findAll(pageable);
        return billLogs.map(log -> {
            BillLogResponse response = new BillLogResponse();
            response.setId(log.getId());
            response.setRemainingBills(log.getRemainingBills());
            response.setCreatedAt(log.getCreatedAt());
            response.setBillId(log.getBill() != null ? log.getBill().getId() : null);
            return response;
        });
    }
}