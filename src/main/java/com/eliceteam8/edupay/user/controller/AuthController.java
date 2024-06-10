package com.eliceteam8.edupay.user.controller;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.eliceteam8.edupay.global.exception.CustomJWTException;
import com.eliceteam8.edupay.user.dto.PasswordTokenDTO;
import com.eliceteam8.edupay.user.dto.SignUpDTO;
import com.eliceteam8.edupay.user.entity.RefreshToken;
import com.eliceteam8.edupay.user.service.AuthService;
import com.eliceteam8.edupay.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;
    private final UserService userService;


    @PostMapping("/sign-up")
    public ResponseEntity<Map<String,Long>> signUp(@Valid @RequestBody SignUpDTO signUpDto ) {
        log.info("signUpDto: {}", signUpDto);
        Long newUserId = authService.signUp(signUpDto);
        return ResponseEntity.status(201).body(Map.of("userId", newUserId));
    }


    @GetMapping("/check-email")
    public ResponseEntity<Map<String,Object>> checkEmailDuplicate(@RequestParam String email) {
        boolean isDuplicate = userService.isEmailDuplicate(email);
        return ResponseEntity.ok(Map.of("result",isDuplicate));
    }



    @PostMapping("/send-password-token")
    public ResponseEntity<Map<String,Object>> sendPasswordResetEmail(@RequestBody PasswordTokenDTO passwordTokenDTO){
        boolean isSend =  userService.sendPasswordResetEmail(
                passwordTokenDTO.getEmail(),
                passwordTokenDTO.getUsername()
        );

        String message =  "인증번호를 발송했습니다. " +
                "인증번호가 오지 않으면 입력하신 정보가 회원정보와 일치하는지 확인해 주세요.";
        return ResponseEntity.ok(Map.of("result",isSend,"message",message));
    }

    @PostMapping("/check-password-token")
    public ResponseEntity<Map<String,Object>> checkResetEmail(@RequestBody PasswordTokenDTO passwordTokenDTO){
        if(passwordTokenDTO.getToken().isBlank()){
            throw new IllegalArgumentException("토큰값이 존재하지 않습니다.");
        }
        boolean result = userService.checkPasswordResetEmail(
                passwordTokenDTO.getToken(),
                passwordTokenDTO.getEmail()
        );
        return ResponseEntity.ok(Map.of("result",result));
    }





}
