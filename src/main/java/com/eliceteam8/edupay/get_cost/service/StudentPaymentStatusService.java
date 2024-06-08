package com.eliceteam8.edupay.get_cost.service;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.repository.AcademyStudentRepository;
import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.bill.domain.Status;
import com.eliceteam8.edupay.bill.repository.BillRepository;
import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusRequestDto;
import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusResponseDto;
import com.eliceteam8.edupay.get_cost.entity.StudentPaymentStatus;
import com.eliceteam8.edupay.get_cost.repository.StudentPaymentStatusRepository;
import com.eliceteam8.edupay.payment.entity.PaymentHistory;
import com.eliceteam8.edupay.payment.repository.PaymentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class StudentPaymentStatusService {

    private final StudentPaymentStatusRepository studentPaymentStatusRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final AcademyStudentRepository academyStudentRepository;
    private final BillRepository billRepository;

    @Autowired
    public StudentPaymentStatusService(StudentPaymentStatusRepository studentPaymentStatusRepository,
                                       PaymentHistoryRepository paymentHistoryRepository,
                                       AcademyStudentRepository academyStudentRepository,
                                       BillRepository billRepository) {
        this.studentPaymentStatusRepository = studentPaymentStatusRepository;
        this.paymentHistoryRepository = paymentHistoryRepository;
        this.academyStudentRepository = academyStudentRepository;
        this.billRepository = billRepository;
    }

    public Page<StudentPaymentStatusResponseDto> getAllPayments(Status status, LocalDateTime startDate, LocalDateTime endDate, String studentName, String phoneNumber, int page, int size) {
        Page<StudentPaymentStatus> payments;
        PageRequest pageRequest = PageRequest.of(page, size);
        if (status == Status.ALL) {
            if (studentName != null) {
                payments = studentPaymentStatusRepository.findByStudent_StudentNameAndUpdatedAtBetween(studentName, startDate, endDate, pageRequest);
            } else if (phoneNumber != null) {
                payments = studentPaymentStatusRepository.findByStudent_PhoneNumberAndUpdatedAtBetween(phoneNumber, startDate, endDate, pageRequest);
            } else {
                payments = studentPaymentStatusRepository.findByUpdatedAtBetween(startDate, endDate, pageRequest);
            }
        } else {
            if (studentName != null) {
                payments = studentPaymentStatusRepository.findByBill_StatusAndUpdatedAtBetweenAndStudent_StudentName(status, startDate, endDate, studentName, pageRequest);
            } else if (phoneNumber != null) {
                payments = studentPaymentStatusRepository.findByBill_StatusAndUpdatedAtBetweenAndStudent_PhoneNumber(status, startDate, endDate, phoneNumber, pageRequest);
            } else {
                payments = studentPaymentStatusRepository.findByBill_StatusAndUpdatedAtBetween(status, startDate, endDate, pageRequest);
            }
        }
        return payments.map(StudentPaymentStatus::toResponseDto);
    }

    public Page<StudentPaymentStatusResponseDto> getPaidPayments(LocalDateTime startDate, LocalDateTime endDate, String studentName, String phoneNumber, int page, int size) {
        return getAllPayments(Status.PAID, startDate, endDate, studentName, phoneNumber, page, size);
    }

    public Page<StudentPaymentStatusResponseDto> getUnpaidPayments(LocalDateTime startDate, LocalDateTime endDate, String studentName, String phoneNumber, int page, int size) {
        return getAllPayments(Status.BEFORE, startDate, endDate, studentName, phoneNumber, page, size);
    }

    public StudentPaymentStatus createPaymentStatus(StudentPaymentStatusRequestDto requestDto) {
        AcademyStudent student = findStudentById(requestDto.getStudentId());
        Bill bill = findBillById(requestDto.getBillId());
        PaymentHistory payment = findPaymentById(requestDto.getPaymentId());

        StudentPaymentStatus paymentStatus = StudentPaymentStatus.fromRequestDto(requestDto, student, bill, payment);
        return studentPaymentStatusRepository.save(paymentStatus);
    }

    public StudentPaymentStatus updatePaymentStatus(Long id, StudentPaymentStatusRequestDto requestDto) {
        StudentPaymentStatus existingStatus = findPaymentStatusById(id);
        AcademyStudent student = findStudentById(requestDto.getStudentId());
        Bill bill = findBillById(requestDto.getBillId());
        PaymentHistory payment = findPaymentById(requestDto.getPaymentId());

        existingStatus.setStudent(student);
        existingStatus.setBill(bill);
        existingStatus.setPayment(payment);
        existingStatus.setUpdatedAt(LocalDateTime.now());

        return studentPaymentStatusRepository.save(existingStatus);
    }

    public void deletePaymentStatus(Long id) {
        StudentPaymentStatus paymentStatus = findPaymentStatusById(id);
        studentPaymentStatusRepository.delete(paymentStatus);
    }

    public StudentPaymentStatus getPaymentStatusById(Long id) {
        return findPaymentStatusById(id);
    }

    private StudentPaymentStatus findPaymentStatusById(Long id) {
        return studentPaymentStatusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("이 아이디로는 수납 상태 못 찾음: " + id));
    }

    private AcademyStudent findStudentById(Long id) {
        return academyStudentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("이 아이디로는 학생 못 찾음: " + id));
    }

    private Bill findBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("이 아이디로는 청구서 못 찾음: " + id));
    }

    private PaymentHistory findPaymentById(Long id) {
        return paymentHistoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("이 아이디로는 결제정보 못 찾음" + id));
    }
}