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





    public void getUserById(Long userId) {


    }

    public boolean isEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public boolean sendPasswordResetEmail(String email,String username) {
        User user = userRepository.findUserByEmailAndUsername(email,username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));
        user.generateToken();

        boolean result = passwordTokenSave(user.getId().toString(), user.getPasswordCheckToken());

        sendEmail(email,user.getPasswordCheckToken());
        return result;
    }

    private boolean passwordTokenSave(String userId, String token){
        try {
            if(redisTemplate.hasKey(userId)){
                return true;
            }
             redisTemplate.opsForValue().setIfAbsent(userId,
                    token,
                    Duration.ofMinutes(5));
            log.info("Redis setIfAbsent result:");
            return true;
        } catch (Exception e) {
            log.error("Error saving refresh token to Redis");
            return false;
        }
    }


    @Async
    public void sendEmail(String email, String token) {

        String content = "<p>안녕하세요 edupay 입니다.</p>"
                + "<p>이메일 인증 번호를 알려드립니다.</p>"
                + "<p>인증번호는 발송된 시점부터 5분간만 유효하니, 확인 후 바로 입력해주세요.</p>"
                + "<h3 style='display: inline-block; padding: 10px; background-color: #f0f0f0; border-radius: 5px; color: #333;'>" + token + "</h3>"
                + "<br>"
                + "<p><small>이 메일은 자동으로 생성된 메일입니다. 회신하지 마세요.</small></p>";

        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject("비밀번호 재설정")
                .message(content)
                .build();

        emailService.sendEmail(emailMessage);
    }



}
