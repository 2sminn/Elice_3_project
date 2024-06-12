package com.eliceteam8.edupay.academy_management.response;

import com.eliceteam8.edupay.academy_management.entity.Lecture;
import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.bill.domain.BillLog;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AcademyStudentResponseDTO {
    //private String academyName;
    private Long academyId;
    private String academyName;
    private Long studentId;
    private String studentName;
    private String birthdate;
    private String phoneNumber;
    private String email;
    private String schoolName;
    private String grade;
    private List<Lecture> Lectures;
    //private List<Bill> bills;
    //private List<BillLog> billLogs;
    private int beforePaymentCount;
    private int paidPaymentCount;

}
