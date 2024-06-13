package com.eliceteam8.edupay.receipt.service;

import com.eliceteam8.edupay.academy_management.entity.AcademyStudent;
import com.eliceteam8.edupay.academy_management.response.AcademyStudentResponseDTO;
import com.eliceteam8.edupay.academy_management.service.AcademyStudentService;
import com.eliceteam8.edupay.academy_management.service.LectureService;
import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.bill.service.BillService;
import com.eliceteam8.edupay.bill.service.EmailService;
import com.eliceteam8.edupay.receipt.dto.ReceiptDto;
import com.eliceteam8.edupay.receipt.entity.ReceiptEntity;
import com.eliceteam8.edupay.receipt.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private AcademyStudentService studentService;
    @Autowired
    private EmailService emailService;

    public void createReceipt(Bill bill) {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setReceiptDate(LocalDateTime.now());
        receiptEntity.setBillId(bill.getId());
        receiptEntity.setStudentId(bill.getStudent().getId());
        receiptRepository.save(receiptEntity);
    }
    public List<ReceiptDto> getReceiptsByInfo(Long studentId, String year, String month) {

        YearMonth targetYearMonth = YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));

        LocalDateTime startDate = targetYearMonth.atDay(1).atStartOfDay();
        LocalDateTime endDate = targetYearMonth.atEndOfMonth().atTime(LocalTime.MAX);

        List<ReceiptEntity> receiptEntities = receiptRepository.findByStudentIdAndReceiptDateBetween(studentId, startDate, endDate);

        return receiptEntities.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    private ReceiptDto convertToDto(ReceiptEntity receiptEntity) {
        return ReceiptDto.builder()
            .receiptId(receiptEntity.getReceiptId())
            .billId(receiptEntity.getBillId())
            .receiptDate(receiptEntity.getReceiptDate())
            .studentName(receiptEntity.getStudent().getStudentName())
            .birthdate(receiptEntity.getBill().getStudent().getBirthDate())
            .lectures(receiptEntity.getStudent().getLectures())
            .totalPrice(receiptEntity.getBill().getTotalPrice())
            .grade(receiptEntity.getStudent().getGrade())
            .build();
   }
    public void sendReceiptByEmail(Long studentId, String year, String month) {
        AcademyStudentResponseDTO student = studentService.getStudentById(studentId);
        String email = student.getEmail();

        String receiptLink = "영수증 조회 링크: http://34.47.70.191//receipt?studentId=" + studentId + "&year=" + year + "&month=" + month;

        String subject = "영수증 발송";
        String text = "영수증 조회 링크 : " + receiptLink;

        emailService.sendSimpleMessage(email, subject, text);
    }

}