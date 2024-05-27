package com.eliceteam8.edupay.academy_management.entity;

import com.eliceteam8.edupay.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "academy")
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String academyName;
    private int postCode;
    private String address;
    private String landlineNumber;
    private String stamp_image_url;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;



    //createAcademy



}
