package com.eliceteam8.edupay.security.handler;

import com.eliceteam8.edupay.security.config.jwt.JwtProvider;
import com.eliceteam8.edupay.user.dto.UserDTO;
import com.eliceteam8.edupay.user.entity.RefreshToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final RedisTemplate<String, String> redisTemplate;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("--- LoginSuccessHandler ---");
        UserDTO userDTO =  (UserDTO)authentication.getPrincipal();

        response.setContentType("application/json;charset=UTF-8");

        // 응답에 포함할 데이터 생성
        Map<String, Object> userClaims = userDTO.getClaims();
        String email = userDTO.getEmail();
        userClaims.remove("password");
        // JWT 토큰 생성


        String accessToken = JwtProvider.generateToken(userClaims, 10);

        String refreshToken = JwtProvider.generateToken(userClaims, 60*12);

        refreshTokenSave(refreshToken, email);


        userClaims.put("refreshToken", refreshToken);
        userClaims.put("accessToken", accessToken);
        // JSON 응답 작성
        response.getWriter().write(objectMapper.writeValueAsString(userClaims));

    }

    private boolean refreshTokenSave( String refreshToken, String email){
        try {
            //redis 키값이 존재하면 저장하지 않음
            if(redisTemplate.getExpire(email) > 600){
                return false;
            }

           redisTemplate.opsForValue().setIfAbsent(
                    email,
                    refreshToken,
                    Duration.ofHours(12));
            return true;
        } catch (Exception e) {
            log.error("Error saving refresh token to Redis : {}", e.getMessage());
            return false;
        }
    }
}
