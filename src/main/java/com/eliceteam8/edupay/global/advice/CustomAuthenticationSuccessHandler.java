package com.eliceteam8.edupay.global.advice;

import com.eliceteam8.edupay.user.dto.UserStudentCreateDTO;
import com.eliceteam8.edupay.user.entity.User;
import com.eliceteam8.edupay.user.entity.UserRole;
import jakarta.servlet.ServletException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Collectors;


public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) authentication.getPrincipal();

        // UserStudentCreateDTO 객체 생성
        UserStudentCreateDTO userStudentCreateDTO = new UserStudentCreateDTO(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(UserRole::name).collect(Collectors.toList()),
                user.getAcademy().getId(),          // 학원 ID
                user.getAcademy().getAcademyName(), // 학원 이름
                user.getId()                       // 사용자 ID
        );

        // 세션에 사용자 정보 저장
        session.setAttribute("user", userStudentCreateDTO);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

