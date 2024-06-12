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
import java.time.YearMonth;

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

    public Page<StudentPaymentStatusResponseDto> getAllPayments(Status status, Integer year, Integer month, String studentName, String phoneNumber, int page, int size) {
        Page<StudentPaymentStatus> payments;
        PageRequest pageRequest = PageRequest.of(page, size);

        if (year != null && month != null) {
            LocalDateTime startDate = YearMonth.of(year, month).atDay(1).atStartOfDay();
            LocalDateTime endDate = YearMonth.of(year, month).atEndOfMonth().atTime(23, 59, 59);

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
        } else {
            if (status == Status.ALL) {
                if (studentName != null) {
                    payments = studentPaymentStatusRepository.findByStudent_StudentName(studentName, pageRequest);
                } else if (phoneNumber != null) {
                    payments = studentPaymentStatusRepository.findByStudent_PhoneNumber(phoneNumber, pageRequest);
                } else {
                    payments = studentPaymentStatusRepository.findAll(pageRequest);
                }
            } else {
                if (studentName != null) {
                    payments = studentPaymentStatusRepository.findByBill_StatusAndStudent_StudentName(status, studentName, pageRequest);
                } else if (phoneNumber != null) {
                    payments = studentPaymentStatusRepository.findByBill_StatusAndStudent_PhoneNumber(status, phoneNumber, pageRequest);
                } else {
                    payments = studentPaymentStatusRepository.findByBill_Status(status, pageRequest);
                }
            }
        }
        return payments.map(StudentPaymentStatusResponseDto::fromEntity);
    }

    public Page<StudentPaymentStatusResponseDto> getPaidPayments(Integer year, Integer month, String studentName, String phoneNumber, int page, int size) {
        return getAllPayments(Status.PAID, year, month, studentName, phoneNumber, page, size);
    }

    public Page<StudentPaymentStatusResponseDto> getUnpaidPayments(Integer year, Integer month, String studentName, String phoneNumber, int page, int size) {
        return getAllPayments(Status.BEFORE, year, month, studentName, phoneNumber, page, size);
    }

    public StudentPaymentStatusResponseDto createPaymentStatus(StudentPaymentStatusRequestDto requestDto) {
        AcademyStudent student = findStudentById(requestDto.getStudentId());
        Bill bill = findBillById(requestDto.getBillId());
        PaymentHistory payment = findPaymentById(requestDto.getPaymentId());

        StudentPaymentStatus paymentStatus = new StudentPaymentStatus(student, bill, payment, LocalDateTime.now());
        return StudentPaymentStatusResponseDto.fromEntity(studentPaymentStatusRepository.save(paymentStatus));
    }

    public StudentPaymentStatusResponseDto updatePaymentStatus(Long id, StudentPaymentStatusRequestDto requestDto) {
        StudentPaymentStatus existingStatus = findPaymentStatusById(id);
        AcademyStudent student = findStudentById(requestDto.getStudentId());
        Bill bill = findBillById(requestDto.getBillId());
        PaymentHistory payment = findPaymentById(requestDto.getPaymentId());

        existingStatus.setStudent(student);
        existingStatus.setBill(bill);
        existingStatus.setPayment(payment);
        existingStatus.setUpdatedAt(LocalDateTime.now());

        return StudentPaymentStatusResponseDto.fromEntity(studentPaymentStatusRepository.save(existingStatus));
    }

    public void deletePaymentStatus(Long id) {
        StudentPaymentStatus paymentStatus = findPaymentStatusById(id);
        studentPaymentStatusRepository.delete(paymentStatus);
    }

    public StudentPaymentStatusResponseDto getPaymentStatusById(Long id) {
        return StudentPaymentStatusResponseDto.fromEntity(findPaymentStatusById(id));
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
                .orElseThrow(() -> new IllegalArgumentException("이 아이디로는 결제정보 못 찾음: " + id));
    }
}
