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

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AcademyRepository academyRepository;
    private final EmailSendService emailService;
    private final RedisTemplate<String, String> redisTemplate;



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
        user.generateToken();

        boolean result = passwordTokenSave(user.getId().toString(), user.getPasswordToken());

        sendEmail(email,user.getPasswordToken());
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
        if(user.isTokenExpired()){
            throw new IllegalStateException("인증번호가 만료되었습니다.");
        }
        if(!user.getPasswordToken().equals(token)){
            throw new IllegalStateException("인증번호가 일치하지 않습니다.");
        }
        String userId = user.getId().toString();
        if(redisTemplate.hasKey(userId)) {
            redisTemplate.delete(userId);
            return true;
        }

        return false;
    }



}
