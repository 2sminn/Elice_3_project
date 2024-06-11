package com.eliceteam8.edupay.user.service;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.repository.AcademyRepository;
import com.eliceteam8.edupay.global.enums.ErrorMessage;
import com.eliceteam8.edupay.global.mail.EmailMessage;
import com.eliceteam8.edupay.global.mail.EmailSendService;
import com.eliceteam8.edupay.user.dto.UpdateUserDTO;
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
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AcademyRepository academyRepository;
    private final EmailSendService emailService;
    private final RedisTemplate<String, String> redisTemplate;

    public static final int MAX_ATTEMPT = 5;
    public static final int BLOCK_TIME = 10 * 60;

    public static final String ATTEMPT_KEY = "pw_attempt_";
    public static final String BLOCK_KEY = "pw_block_";
    public static final String USER_KEY = "pw_";

    //업데이트할 유저 정보 가져오기
    public UpdateUserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.NOT_FOUND_USER));

        UpdateUserDTO updateUserDTO = UpdateUserDTO.entityToDTO(user);

        return updateUserDTO;
    }

    public UpdateUserDTO getUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.NOT_FOUND_USER));
        UpdateUserDTO updateUserDTO = UpdateUserDTO.entityToDTO(user);
        return updateUserDTO;
    }

    //유저 정보 업데이트
    @Transactional
    public Long updateUserAndAcademy(UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(updateUserDTO.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.NOT_FOUND_USER));

        Academy academy = academyRepository.findById(updateUserDTO.getAcademyId())
                .orElseThrow(() -> new UsernameNotFoundException("해당 학원을 찾을 수 없습니다."));

        user.updateUser(updateUserDTO);
        academy.updateAcademy(updateUserDTO);
        academy.setUser(user);

        return user.getId();

    }

    public boolean isEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public boolean sendPasswordResetEmail(String email,String username) {
        User user = userRepository.findUserByEmailAndUsername(email,username)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.NOT_FOUND_USER));

        String userId = user.getId().toString();
        String attemptsKey = ATTEMPT_KEY + userId;
        String blockKey = BLOCK_KEY + userId;

        //블록된 유저인지 확인
        if(redisTemplate.hasKey(blockKey)){
            throw new IllegalStateException("비밀번호 찾기 시도 횟수 초과로 30분간 이용이 제한됩니다.");
        }


        //몇번째 시도인지 확인
        String attemptsCountStr = redisTemplate.opsForValue().get(attemptsKey);
        int attemptsCount = attemptsCountStr != null ? Integer.parseInt(attemptsCountStr) : 0;

        //시도횟수 초과시 30분간 이용 제한
        if(attemptsCount >= MAX_ATTEMPT){
            redisTemplate.opsForValue().set(blockKey,
                    String.valueOf(Instant.now().getEpochSecond() + BLOCK_TIME), BLOCK_TIME, TimeUnit.SECONDS);
            redisTemplate.delete(attemptsKey);

            throw new IllegalStateException("비밀번호 찾기 시도 횟수 초과로 30분간 이용이 제한됩니다.");

        }

        redisTemplate.opsForValue().increment(attemptsKey);
        redisTemplate.expire(attemptsKey, BLOCK_TIME, TimeUnit.SECONDS);

        String token = UUID.randomUUID().toString().substring(0, 5);
        boolean result = passwordTokenSave(userId, token);

        sendEmail(email,token);
        return result;
    }

    private boolean passwordTokenSave(String userId, String token){
        String userKey = USER_KEY + userId;
        try {
            if(redisTemplate.hasKey(userId)){
                return true;
            }
             redisTemplate.opsForValue().setIfAbsent(userKey,
                    token,
                    Duration.ofMinutes(5));
            return true;
        } catch (Exception e) {
            log.error("Error saving refresh token to Redis");
            throw e;
        }
    }



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


    public boolean checkPasswordResetEmail(String token, String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.NOT_FOUND_USER));

        String userId = USER_KEY+ user.getId().toString();
        String savedToken = redisTemplate.opsForValue().get(userId);
        if(savedToken == null){
            throw new IllegalStateException("인증번호가 만료되었습니다.");
        }
        if(!savedToken.equals(token)){
            throw new IllegalStateException("인증번호가 일치하지 않습니다.");
        }

        redisTemplate.delete(userId);
        return true;
    }



}
