package com.eliceteam8.edupay.user.controller;

import com.eliceteam8.edupay.user.dto.SignUpDTO;
import com.eliceteam8.edupay.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDto) {
        authService.signUp(signUpDto);
        return ResponseEntity.status(201).body("success");
    }



}
