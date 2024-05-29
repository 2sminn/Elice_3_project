package com.eliceteam8.edupay.security.handler;

import com.eliceteam8.edupay.config.jwt.JwtProvider;
import com.eliceteam8.edupay.user.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("--- LoginSuccessHandler ---");
        UserDTO userDTO =  (UserDTO)authentication.getPrincipal();

        response.setContentType("application/json;charset=UTF-8");

        // 응답에 포함할 데이터 생성
        Map<String, Object> userClaims = userDTO.getClaims();
        // JWT 토큰 생성
        String accessToken = JwtProvider.generateToken(userClaims, 720);
        userClaims.put("accessToken", accessToken);

        userClaims.remove("password");
        // JSON 응답 작성
        response.getWriter().write(objectMapper.writeValueAsString(userClaims));
        response.getWriter().flush();
    }
}
