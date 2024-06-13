package com.eliceteam8.edupay.user.entity;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.global.exception.NotEnoughPointsException;
import com.eliceteam8.edupay.user.dto.SignUpDTO;
import com.eliceteam8.edupay.user.dto.UpdateUserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    private String phoneNumber;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<UserRole> roles = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Academy academy;

    private Long point;

    private String passwordToken;
    private LocalDateTime passwordTokenAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;


    //createUser
    public static User createUser(SignUpDTO signUpDto) {
        return User.builder()
                .password(signUpDto.getPassword())
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .phoneNumber(signUpDto.getPhoneNumber())
                .point(0L)
                .build();
    }

    public void updateUser(UpdateUserDTO updateUserDTO) {
        this.username = updateUserDTO.getUsername();
        this.phoneNumber = updateUserDTO.getPhoneNumber();
    }

    public void addRole(UserRole userRole) {
        roles.add(userRole);
    }

    public void addPoint(Long pointToAdd) {
        this.point += pointToAdd;
    }

    public void usePoint(Long usedPoint) {
        if (this.point < usedPoint) {
            throw new NotEnoughPointsException("포인트가 부족합니다.");
        }
        this.point -= usedPoint;
    }

    public void generateToken() {
        this.passwordToken = UUID.randomUUID().toString().substring(0, 5);
        this.passwordTokenAt = LocalDateTime.now();
    }

    public boolean isTokenExpired() {
        return passwordTokenAt.plusMinutes(5).isBefore(LocalDateTime.now());
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
