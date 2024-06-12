package com.eliceteam8.edupay.user.controller;


import com.eliceteam8.edupay.user.dto.*;
import com.eliceteam8.edupay.user.service.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<UserAcademyDTO> getUser(){
        UserAcademyDTO userAcademyDTO = userService.getUser();
        return ResponseEntity.ok(userAcademyDTO);
    }

    
    //유저정보 수정
    @PutMapping
    public ResponseEntity<UserIdResponseDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        Long updatedUserId = userService.updateUserAndAcademy(updateUserDTO);
        UserIdResponseDTO responseDTO = UserIdResponseDTO.builder()
                .userId(updatedUserId)
                .result("success")
                .build();
        return ResponseEntity.ok(responseDTO);
    }

    //비밀번호변경
    @PatchMapping("/password")
    public ResponseEntity<BooleanResultDTO> updatePassword(@Valid @RequestBody PasswordDTO passwordDTO ) {
        if(!passwordDTO.getPassword().equals(passwordDTO.getConfirmPassword())){
           throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        Long id = userService.updatePassword(passwordDTO);
        String message = "ID:"+id+" 님 비밀번호가 변경되었습니다.";
        BooleanResultDTO result = BooleanResultDTO.builder().result(true).message(message).build();
        return ResponseEntity.ok(result);

    }


    @GetMapping("/test")
    public String test( @RequestParam(name = "flag")Integer flag    ) {
        return "test";
    }
}
