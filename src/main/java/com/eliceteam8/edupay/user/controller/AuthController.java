package com.eliceteam8.edupay.user.controller;

import com.eliceteam8.edupay.user.dto.SignUpDTO;
import com.eliceteam8.edupay.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDTO signUpDto, BindingResult bindingResult ) {


        Long newUserId = authService.signUp(signUpDto);
        return ResponseEntity.status(201).body(newUserId);
    }



}
