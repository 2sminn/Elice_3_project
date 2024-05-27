package com.eliceteam8.edupay.user.entity;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.user.dto.SignUpDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    private String password;
    private String username;
    private String email;
    private String phoneNumber;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<UserRole> roleList = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Academy academy;



    //createUser
    public static User createUser(SignUpDTO signUpDto) {
        return User.builder()
                .loginId(signUpDto.getLoginId())
                .password(signUpDto.getPassword())
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .phoneNumber(signUpDto.getPhoneNumber())

                .build();
    }


    public void addRole(UserRole userRole){
        roleList.add(userRole);
    }

}
