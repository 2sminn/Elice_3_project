package com.eliceteam8.edupay.user.controller;


import com.eliceteam8.edupay.user.dto.UpdateUserDTO;
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

    @GetMapping("/{userId}")
    public ResponseEntity<UpdateUserDTO> getUserById(@PathVariable("userId") Long userId) {
        UpdateUserDTO updateUserDTO = userService.getUserById(userId);
        return ResponseEntity.ok(updateUserDTO);
    }

    
    //유저정보 수정
    @PutMapping("/{userId}")
    public ResponseEntity<Map<String,Object>> updateUser(@PathVariable("userId") Long userId, @RequestBody UpdateUserDTO updateUserDTO) {
        Long updatedUserId = userService.updateUserAndAcademy(userId, updateUserDTO);
        return ResponseEntity.ok(Map.of("userId", updatedUserId,"result","success"));
    }

    
    //비밀번호변경
    
    
    




    @GetMapping("/test")
    public String test( @RequestParam(name = "flag")Integer flag    ) {
        return "test";
    }
}
