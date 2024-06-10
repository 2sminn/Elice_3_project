package com.eliceteam8.edupay.academy_management.entity;

import com.eliceteam8.edupay.bill.domain.Bill;
import com.eliceteam8.edupay.user.dto.SignUpDTO;
import com.eliceteam8.edupay.user.dto.UpdateUserDTO;
import com.eliceteam8.edupay.user.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private String academyName;

    @Column(nullable = false, unique = true)
    private String businessNumber;

    @Column(nullable = false)
    private String academyEmail;

    @Column(nullable = false)
    private int zipCode;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String addressDetail;

    @Column(nullable = false)
    private String landlineNumber;

    private String stampImageUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "academy")
    private List<Bill> bill = new ArrayList<>();

    @OneToMany(mappedBy = "academy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AcademyStudent> academyStudents;

    @OneToMany(mappedBy = "academy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Lecture> lectures;
    //createAcademy



    public void updateAcademy(UpdateUserDTO updateAcademyDTO) {
        this.academyName = updateAcademyDTO.getAcademyName();
        this.academyEmail = updateAcademyDTO.getAcademyEmail();
        this.zipCode = updateAcademyDTO.getZipCode();
        this.address = updateAcademyDTO.getAddress();
        this.addressDetail = updateAcademyDTO.getAddressDetail();
        this.landlineNumber = updateAcademyDTO.getLandlineNumber();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
