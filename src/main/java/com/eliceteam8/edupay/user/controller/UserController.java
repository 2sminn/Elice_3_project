package com.eliceteam8.edupay.user.controller;


import com.eliceteam8.edupay.user.service.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId) {

        userService.getUserById(userId);

        return null;
    }


    @GetMapping("/check-email")
    public ResponseEntity<Map<String,Boolean>> checkEmailDuplicate(@RequestParam String email) {
        boolean isDuplicate = userService.isEmailDuplicate(email);
        return ResponseEntity.ok(Map.of("result",isDuplicate));
    }


    @GetMapping("/reset-password")
    public ResponseEntity<Boolean> sendPasswordResetEmail(@RequestParam String email) {
        userService.sendPasswordResetEmail(email);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/check-reset-password")
    public ResponseEntity<Boolean> checkResetEmail(@RequestParam String email) {
        userService.sendPasswordResetEmail(email);
        return ResponseEntity.ok(true);
    }



    @GetMapping("/test")
    public String test( @RequestParam(name = "flag")Integer flag    ) {
        return "test";
    }
}
