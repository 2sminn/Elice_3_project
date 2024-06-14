package com.eliceteam8.edupay.user.service;

import com.eliceteam8.edupay.academy_management.dto.response.AcademyCountDTO;
import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.repository.AcademyRepository;
import com.eliceteam8.edupay.academy_management.service.AcademyService;
import com.eliceteam8.edupay.global.enums.ErrorMessage;
import com.eliceteam8.edupay.global.mail.EmailMessage;
import com.eliceteam8.edupay.global.mail.EmailSendService;
import com.eliceteam8.edupay.user.dto.PasswordDTO;
import com.eliceteam8.edupay.user.dto.UpdateUserDTO;
import com.eliceteam8.edupay.user.dto.UserAcademyDTO;
import com.eliceteam8.edupay.user.dto.UserDTO;
import com.eliceteam8.edupay.user.entity.PasswordToken;
import com.eliceteam8.edupay.user.entity.User;
import com.eliceteam8.edupay.user.repository.PasswordTokenRepository;
import com.eliceteam8.edupay.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AcademyRepository academyRepository;
    private final AcademyService academyService;
    private final EmailSendService emailService;
    private final PasswordTokenRepository passwordTokenRepository;
    private final PasswordEncoder passwordEncoder;
    public static final int MAX_ATTEMPT = 5;
    public static final int BLOCK_TIME = 10 * 60;
    public static final int TTL = 60 * 5;


    //업데이트할 유저 정보 가져오기
    public UpdateUserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.NOT_FOUND_USER));

        UpdateUserDTO updateUserDTO = UpdateUserDTO.entityToDTO(user);

        return updateUserDTO;
    }

    @Transactional(readOnly = true)
    public UserAcademyDTO getUser() {
        UserDTO userDTO = (UserDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.NOT_FOUND_USER));

        UserAcademyDTO userAcademyDTO = UserAcademyDTO.entityToDTO(user);

        AcademyCountDTO studentAndLectureCount = academyService.getStudentAndLectureCount();

        userAcademyDTO.setTotalPaidBill(studentAndLectureCount.getTotalPaidBill());
        userAcademyDTO.setLectureCount(studentAndLectureCount.getLectureCount());
        userAcademyDTO.setStudentCount(studentAndLectureCount.getStudentCount());

        return userAcademyDTO;
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

        String userId =  user.getId().toString();

        PasswordToken passwordToken = passwordTokenRepository.findById(userId).orElse(new PasswordToken(userId));
        long currentTime = Instant.now().getEpochSecond();

        //블록된 유저인지 확인
        if(passwordToken.getBlock() > currentTime){
            throw new IllegalStateException("비밀번호 찾기 시도가 많아 30분간 이용이 제한됩니다.");
        }

        //몇번째 시도인지 확인
        //시도횟수 초과시 30분간 이용 제한
        if(passwordToken.getAttempt() >= MAX_ATTEMPT){
            passwordToken.setBlock(currentTime + BLOCK_TIME);
            passwordToken.setAttempt(0);
            passwordToken.setExpiration(BLOCK_TIME);
            passwordTokenRepository.save(passwordToken);
            throw new IllegalStateException("비밀번호 찾기 시도가 많아 30분간 이용이 제한됩니다.");
        }

        String token = UUID.randomUUID().toString().substring(0, 5);
        passwordToken.setAttempt(passwordToken.getAttempt() + 1);
        passwordToken.setUserId(userId);
        passwordToken.setToken(token);
        passwordToken.setExpiration(TTL);
        passwordTokenRepository.save(passwordToken);

        sendEmail(email,token);
        return true;
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

        String userId = user.getId().toString();
        PasswordToken passwordToken = passwordTokenRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("인증번호가 만료되었습니다.")
        );

        if(!passwordToken.getToken().equals(token)){
            throw new IllegalStateException("인증번호가 일치하지 않습니다.");
        }
        passwordTokenRepository.delete(passwordToken);
        return true;
    }


    @Transactional
    public Long updatePassword(PasswordDTO passwordDTO) {
        UserDTO user = (UserDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User findUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.NOT_FOUND_USER));

            if(passwordEncoder.matches(passwordDTO.getPassword(),findUser.getPassword())){
                throw new IllegalArgumentException("기존 비밀번호와 동일합니다.");
            }
            findUser.updatePassword(passwordEncoder.encode(passwordDTO.getPassword()));

            return findUser.getId();
    }
}
