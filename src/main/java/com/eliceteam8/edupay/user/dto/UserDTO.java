package com.eliceteam8.edupay.user.dto;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;

@Getter
public class UserDTO extends User {



    private String email;
    private String phoneNumber;

    private String loginId;

    private String academyName;

    private List<String> roleNames = new ArrayList<>();


    public UserDTO(String loginId, String password, String email, String phoneNumber, List<String> roleNames) {
        super(
                loginId,
                password,
                roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_"+str)).collect(Collectors.toList()));

        this.loginId = loginId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roleNames = roleNames;
    }


    public Map<String, Object> getClaims() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("email", email);
        dataMap.put("phoneNumber", phoneNumber);
        dataMap.put("loginId", loginId);
        dataMap.put("roleNames", roleNames);
        return dataMap;
    }


}
