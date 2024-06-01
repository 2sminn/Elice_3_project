package com.eliceteam8.edupay.user.service;

import com.eliceteam8.edupay.academy_management.entity.Academy;
import com.eliceteam8.edupay.academy_management.repository.AcademyRepository;
import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.eliceteam8.edupay.global.exception.AlreadyExistUserException;
import com.eliceteam8.edupay.user.dto.SignUpDTO;
import com.eliceteam8.edupay.user.dto.UserDTO;
import com.eliceteam8.edupay.user.entity.User;
import com.eliceteam8.edupay.user.entity.UserRole;
import com.eliceteam8.edupay.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {


    private final AcademyRepository academyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //로그인
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username: " + username);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found User"));

        UserDTO userDto = new UserDTO(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(userRole -> userRole.name()).toList(),
                user.getAcademy().getId(),
                user.getId()
        );

        return userDto;
    }

    //회원가입
    @Transactional
    public Long signUp(SignUpDTO signUpDto) {

        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new AlreadyExistUserException(ExceptionCode.ALREADY_EXIST_EMAIL);
        }

        String pw = passwordEncoder.encode(signUpDto.getPassword());
        signUpDto.setPassword(pw);
        User user = User.createUser(signUpDto);
        user.addRole(UserRole.USER);

        userRepository.save(user);

        Academy academy = Academy.builder()
                .academyName(signUpDto.getAcademyName())
                .zipCode(signUpDto.getZipCode())
                .address(signUpDto.getAddress())
                .addressDetail(signUpDto.getAddressDetail())
                .landlineNumber(signUpDto.getLandlineNumber())
                .academyEmail(signUpDto.getAcademyEmail())
                .businessNumber(signUpDto.getBusinessNumber())
                .user(user)
                .build();

        academyRepository.save(academy);

        return user.getId();

    }


}
