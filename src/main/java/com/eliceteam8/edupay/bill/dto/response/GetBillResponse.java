package com.eliceteam8.edupay.bill.dto.response;

import com.eliceteam8.edupay.academy_management.lecture.dto.request.LectureDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetBillResponse {
    private Long id;
    private long totalPrice;
    private List<LectureDetail> lectureDetails;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private String message;

    private Long academyId;
    private Long studentId;
}
