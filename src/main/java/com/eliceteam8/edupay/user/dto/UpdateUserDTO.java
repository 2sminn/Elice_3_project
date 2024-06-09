package com.eliceteam8.edupay.user.dto;

import com.eliceteam8.edupay.global.enums.ErrorMessage;
import com.eliceteam8.edupay.user.entity.User;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateUserDTO {


    @NotNull
    private Long userId;
    @NotNull
    private Long academyId;

    @NotBlank
    @Size(min = 2, max = 20)
    private String username;

    @Pattern(regexp = "^010-(\\d{3}|\\d{4})-(\\d{4})$",message = ErrorMessage.INVALID_PHONE_NUMBER)
    private String phoneNumber;

    @NotBlank
    private String academyName;

    @NotBlank
    private String businessNumber;

    @Email(message = ErrorMessage.INVALID_EMAIL)
    @NotBlank
    private String academyEmail;

    @NotNull
    private int zipCode;

    private Long point;

    @NotBlank
    private String address;

    @NotBlank
    private String addressDetail;

    @NotEmpty
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",message = ErrorMessage.INVALID_PHONE_NUMBER)
    private String landlineNumber;



    public static UpdateUserDTO entityToDTO(User user){
        return UpdateUserDTO.builder()
                .userId(user.getId())
                .academyId(user.getAcademy().getId())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .academyName(user.getAcademy().getAcademyName())
                .businessNumber(user.getAcademy().getBusinessNumber())
                .academyEmail(user.getAcademy().getAcademyEmail())
                .zipCode(user.getAcademy().getZipCode())
                .address(user.getAcademy().getAddress())
                .addressDetail(user.getAcademy().getAddressDetail())
                .landlineNumber(user.getAcademy().getLandlineNumber())
                .point(user.getPoint())
                .build();
    }

}
