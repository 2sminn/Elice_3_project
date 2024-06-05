package com.eliceteam8.edupay.user.service;

import com.eliceteam8.edupay.global.mail.EmailMessage;
import com.eliceteam8.edupay.global.mail.EmailSendService;
import com.eliceteam8.edupay.user.dto.UserDTO;
import com.eliceteam8.edupay.user.entity.User;
import com.eliceteam8.edupay.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EmailSendService emailService;
    private final RedisTemplate<String, String> redisTemplate;

    @Value("${email.password-reset.url}")
    private String url;



    public void getUserById(Long userId) {
        userRepository.

    }

    public boolean isEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public void sendPasswordResetEmail(String email) {

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found User"));
        user.generateToken();

        passwordTokenSave(user.getId().toString(), user.getPasswordCheckToken());

        sendEmail(email);
    }

    private boolean passwordTokenSave(String userId, String token){



        try {
            redisTemplate.delete(userId);
            boolean result =  redisTemplate.opsForValue().setIfAbsent(userId,
                    token,
                    Duration.ofMinutes(5));
            log.info("Redis setIfAbsent result: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error saving refresh token to Redis", e);
            return false;
        }
    }


    public void sendEmail(String email) {

        String content = "<p>안녕하세요,</p>"
                + "<p>비밀번호 재설정을 요청하셨습니다.</p>"
                + "<p>아래 링크를 클릭하여 비밀번호를 재설정하세요:</p>"
                + "<p><a href=\"" + url + "\">비밀번호 재설정</a></p>"
                + "<br>"
                + "<p>비밀번호를 기억하고 있거나, 비밀번호 재설정을 요청하지 않으셨다면 이 이메일을 무시하세요.</p>";

        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject("비밀번호 재설정")
                .message(content)
                .build();

        emailService.sendEmail(emailMessage);
    }



}
