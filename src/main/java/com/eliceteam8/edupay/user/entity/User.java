package com.eliceteam8.edupay.user.entity;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.user.dto.SignUpDTO;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
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


    private String passwordCheckToken;
//    private LocalDateTime passwordCheckTokenCreatedAt;

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
                .build();
    }


    public void addRole(UserRole userRole){
        roles.add(userRole);
    }


    public void generateToken() {
        this.passwordCheckToken = UUID.randomUUID().toString();
      //  this.passwordCheckTokenCreatedAt = LocalDateTime.now();
    }
}
