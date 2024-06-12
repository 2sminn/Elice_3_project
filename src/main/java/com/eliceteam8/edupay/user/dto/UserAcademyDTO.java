package com.eliceteam8.edupay.user.dto;

import com.eliceteam8.edupay.global.enums.ErrorMessage;
import com.eliceteam8.edupay.user.entity.User;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserAcademyDTO {

    private Long userId;

    private Long academyId;

    private String username;

    private String phoneNumber;

    private String academyName;

    private String businessNumber;

    private String academyEmail;

    private int zipCode;

    private Long point;

    private String address;

    private String addressDetail;

    private String landlineNumber;

    private Long studentCount;
    private Long lectureCount;
    private Long totalPaidBill;

    public static UserAcademyDTO entityToDTO(User user){

        return UserAcademyDTO.builder()
                .userId(user.getId())
                .academyId(user.getAcademy().getId())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .academyName(user.getAcademy().getAcademyName())
                .businessNumber(user.getAcademy().getBusinessNumber())
                .academyEmail(user.getAcademy().getAcademyEmail())
                .zipCode(user.getAcademy().getZipCode())
                .point(user.getPoint())
                .address(user.getAcademy().getAddress())
                .addressDetail(user.getAcademy().getAddressDetail())
                .landlineNumber(user.getAcademy().getLandlineNumber())

                .build();

    }

}
