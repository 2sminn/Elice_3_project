package com.eliceteam8.edupay.academy_management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademyCountDTO {
    private Long academyId;
    private Long studentCount;
    private Long lectureCount;
    private Long totalPaidBill;



    public AcademyCountDTO(Long academyId, Long studentCount, Long lectureCount) {
        this.academyId = academyId;
        this.studentCount = studentCount;
        this.lectureCount = lectureCount;

    }

}
