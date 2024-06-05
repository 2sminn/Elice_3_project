package com.eliceteam8.edupay.user.controller;


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
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId) {
        userService.getUserById(userId);
        return null;
    }

//유효하지
    @GetMapping("/check-email")
    public ResponseEntity<Map<String,Object>> checkEmailDuplicate(@RequestParam String email) {
        boolean isDuplicate = userService.isEmailDuplicate(email);
        return ResponseEntity.ok(Map.of("result",isDuplicate));
    }


    @GetMapping("/reset-password")
    public ResponseEntity<Map<String,Object>> sendPasswordResetEmail(@RequestParam String email,@RequestParam String username) {
        boolean isSend =  userService.sendPasswordResetEmail(email,username);

        String message =  "인증번호를 발송했습니다. " +
                "인증번호가 오지 않으면 입력하신 정보가 회원정보와 일치하는지 확인해 주세요.";
        return ResponseEntity.ok(Map.of("result",isSend,"message",message));
    }

    @GetMapping("/check-reset-password")
    public ResponseEntity<Boolean> checkResetEmail(@RequestParam String token, Principal principal){
        log.info("token : {}",token);
        log.info("principal : {}",principal.getName());
       // userService.checkPasswordResetEmail(token);
        return ResponseEntity.ok(true);
    }



    @GetMapping("/test")
    public String test( @RequestParam(name = "flag")Integer flag    ) {
        return "test";
    }
}
