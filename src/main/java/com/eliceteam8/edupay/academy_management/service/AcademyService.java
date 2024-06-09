package com.eliceteam8.edupay.academy_management.service;

import com.eliceteam8.edupay.academy_management.dto.response.AcademyCountDTO;
import com.eliceteam8.edupay.academy_management.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AcademyService {

    private final AcademyRepository academyRepository;

    public AcademyCountDTO getStudentAndLectureCount(Long academyId){
        //학생수, 강의수 , 정산예정금액 조회
        AcademyCountDTO academyCountDTO = academyRepository.countStudentAndLecture(academyId);
        Long sumPaidBill  = academyRepository.sumPaidBill(academyId);
        if (sumPaidBill == null) {
            academyCountDTO.setTotalPaidBill(0L);
        } else {
            academyCountDTO.setTotalPaidBill(sumPaidBill);
        }

        return academyCountDTO;
    }
}
