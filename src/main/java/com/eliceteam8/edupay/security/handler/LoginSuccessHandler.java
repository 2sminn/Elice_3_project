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


    private static final long EXPIRATION_THRESHOLD = 600L;
    private static final long TOKEN_EXPIRATION_HOURS = 12L;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("--- LoginSuccessHandler ---");
        UserDTO userDTO =  (UserDTO)authentication.getPrincipal();

        response.setContentType("application/json;charset=UTF-8");

        // 응답에 포함할 데이터 생성
        Map<String, Object> userClaims = userDTO.getClaims();
        String userId = userClaims.get("userId").toString();
        userClaims.remove("password");
        // JWT 토큰 생성


        String accessToken = JwtProvider.generateToken(userClaims, 15);

        String refreshToken = JwtProvider.generateToken(userClaims, 60*12);

        // Redis에 저장
        refreshTokenSave(refreshToken, userId);


        userClaims.put("refreshToken", refreshToken);
        userClaims.put("accessToken", accessToken);
        // JSON 응답 작성
        response.getWriter().write(objectMapper.writeValueAsString(userClaims));

    }

    private boolean refreshTokenSave( String refreshToken, String userId){
        try {
            String redisKey = "token_"+userId;
            Long expire = redisTemplate.getExpire(redisKey);
            // Redis 키가 존재하고, 만료 시간이 600초 이상인 경우 저장하지 않음
            if(expire != null && expire > EXPIRATION_THRESHOLD){
                return false;
            }

           redisTemplate.opsForValue().setIfAbsent(
                   redisKey,
                    refreshToken,
                    Duration.ofHours(TOKEN_EXPIRATION_HOURS));
            return true;
        } catch (Exception e) {
            log.error("Error saving refresh token to Redis : email: {}, {}", userId, e.getMessage());
            return false;
        }
    }
}
