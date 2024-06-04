package com.eliceteam8.edupay.user.controller;


import com.eliceteam8.edupay.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailDuplicate(@RequestParam String email) {
        boolean isDuplicate = userService.isEmailDuplicate(email);
        return ResponseEntity.ok(isDuplicate);
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
