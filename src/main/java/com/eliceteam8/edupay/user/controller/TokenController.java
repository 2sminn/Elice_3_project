package com.eliceteam8.edupay.user.controller;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.eliceteam8.edupay.global.exception.CustomJWTException;
import com.eliceteam8.edupay.security.config.jwt.JwtProvider;
import com.eliceteam8.edupay.user.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TokenController {
    private final RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/token/refresh")
    public ResponseEntity<Map<String,Object>> refresh(@RequestHeader("Authorization")String authHeader ,
                                                      @RequestBody RefreshToken refreshToken){
        if(refreshToken.getEmail() == null){
            throw new CustomJWTException(ExceptionCode.INVALID_TOKEN);
        }

        if(authHeader == null || authHeader.length() < 7){
            throw new CustomJWTException(ExceptionCode.INVALID_TOKEN);
        }

        String accessToken = authHeader.substring(7);

        Map<String, Object> accessTokenClaims = JwtProvider.validateToken(accessToken);
        String email = (String) accessTokenClaims.get("email");
        if(email.equals(refreshToken.getEmail()) == false){
            throw new CustomJWTException(ExceptionCode.INVALID_TOKEN);
        }

        String redisRefreshToken = redisTemplate.opsForValue().get(email);

        Map<String, Object> claims = JwtProvider.validateToken(redisRefreshToken);
        String newAccessToken = JwtProvider.generateToken(claims,10);
        String newRefreshToken = checkTime((Integer)claims.get("exp")) == true? JwtProvider.generateToken(claims,60*12):redisRefreshToken;


        return ResponseEntity.ok(Map.of("accessToken",newAccessToken,"refreshToken",newRefreshToken));
    }
    private boolean checkTime(Integer exp) {

        //exp를 날짜로 변환
        Date expDate = new Date((long) exp * 1000);
        //현재 시간과의 차이 계산
        long gap = expDate.getTime() - System.currentTimeMillis();

        //분단위 계산
        long leftMin = gap / 60000;

        return leftMin < 60;
    }
    private boolean checkExpiredToken(String accessToken) {
        try {
            JwtProvider.validateToken(accessToken);
        }catch (CustomJWTException e){
            throw new CustomJWTException(e.getExceptionCode());
        }
        return false;
    }
}
