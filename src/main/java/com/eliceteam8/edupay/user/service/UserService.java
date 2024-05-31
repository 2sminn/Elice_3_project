package com.eliceteam8.edupay.user.service;

import com.eliceteam8.edupay.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public boolean isEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }
}
