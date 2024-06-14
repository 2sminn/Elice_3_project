package com.eliceteam8.edupay.user.dto;

import lombok.Getter;
import lombok.Setter;
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

    private String password;

    private Long academyId;
    private Long userId;


    private List<String> roleNames = new ArrayList<>();


    public UserDTO(String email, String password,
                   List<String> roleNames, Long academyId, Long userId) {
        super(
                email,
                password,
                roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_"+str)).collect(Collectors.toList()));

        this.email = email;
        this.roleNames = roleNames;
        this.academyId=academyId;
        this.userId=userId;
        this.password=password;
    }


    public Map<String, Object> getClaims() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("email", email);
        dataMap.put("roles", roleNames);
        dataMap.put("academyId", academyId);
        dataMap.put("userId", userId);
        dataMap.put("password", password);
        return dataMap;
    }
    public Map<String, Object> getRefreshClaims() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", userId);
        return dataMap;
    }

}
