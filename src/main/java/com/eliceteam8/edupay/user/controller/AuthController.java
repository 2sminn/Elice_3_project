package com.eliceteam8.edupay.user.controller;

import com.eliceteam8.edupay.user.dto.*;
import com.eliceteam8.edupay.user.service.AuthService;
import com.eliceteam8.edupay.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;
    private final UserService userService;


    @PostMapping("/sign-up")
    public ResponseEntity<UserIdResponseDTO> signUp(@Valid @RequestBody SignUpDTO signUpDto ) {
        Long newUserId = authService.signUp(signUpDto);
        UserIdResponseDTO responseDTO = UserIdResponseDTO.builder()
                .userId(newUserId)
                .result("success")
                .build();
        return ResponseEntity.status(201).body(responseDTO);
    }


    @GetMapping("/check-email")
    public ResponseEntity<BooleanResultDTO> checkEmailDuplicate(@RequestParam String email) {
        boolean isDuplicate = userService.isEmailDuplicate(email);
        BooleanResultDTO result = BooleanResultDTO.builder().result(isDuplicate).build();
        return ResponseEntity.ok(result);
    }



    @PostMapping("/send-password-token")
    public ResponseEntity<BooleanResultDTO> sendPasswordResetEmail(@RequestBody PasswordTokenDTO passwordTokenDTO){
        boolean isSend =  userService.sendPasswordResetEmail(
                passwordTokenDTO.getEmail(),
                passwordTokenDTO.getUsername()
        );

        String message =  "인증번호를 발송했습니다. " +
                "인증번호가 오지 않으면 입력하신 정보가 회원정보와 일치하는지 확인해 주세요.";
        BooleanResultDTO result = BooleanResultDTO.builder().result(isSend).message(message).build();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/check-password-token")
    public ResponseEntity<BooleanResultDTO> checkResetEmail(@RequestBody PasswordTokenDTO passwordTokenDTO){
        if(passwordTokenDTO.getToken().isBlank()){
            throw new IllegalArgumentException("토큰값이 존재하지 않습니다.");
        }
        boolean result = userService.checkPasswordResetEmail(
                passwordTokenDTO.getToken(),
                passwordTokenDTO.getEmail()
        );
        BooleanResultDTO booleanResultDTO = BooleanResultDTO.builder().result(result).build();
        return ResponseEntity.ok(booleanResultDTO);
    }


    @PatchMapping("/password")
    public ResponseEntity<BooleanResultDTO> updatePassword(@Valid @RequestBody PasswordDTO passwordDTO ) {
        if(!passwordDTO.getPassword().equals(passwordDTO.getConfirmPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        if(passwordDTO.getEmail() == null){
            throw new IllegalArgumentException("이메일이 존재하지 않습니다.");
        }
        Long id = authService.updatePassword(passwordDTO);
        String message = "ID:"+id+" 님 비밀번호가 변경되었습니다.";
        BooleanResultDTO result = BooleanResultDTO.builder().result(true).message(message).build();
        return ResponseEntity.ok(result);

    }




}
