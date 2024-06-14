package com.eliceteam8.edupay.user.controller;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.eliceteam8.edupay.global.exception.CustomJWTException;
import com.eliceteam8.edupay.global.exception.NotFoundRefreshTokenException;
import com.eliceteam8.edupay.security.config.jwt.JwtProvider;
import com.eliceteam8.edupay.user.dto.CreateTokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TokenController {
    private final RedisTemplate<String, String> redisTemplate;
    private static final String REFRESH_TOKEN_PREFIX = "refreshToken_";
    private static final int ACCESS_TOKEN_EXPIRATION = 60*12;
    private static final int REFRESH_TOKEN_EXPIRATION = 60 * 24;
    private static final int REFRESH_TOKEN_RENEW_THRESHOLD = 60;


    @RequestMapping("/token/refresh")
    public Map<String,Object> refresh(@RequestHeader("Authorization")String authHeader, @RequestBody CreateTokenDTO createTokenDTO){


//        if(authHeader == null || authHeader.length() < 7){
//            throw new CustomJWTException(ExceptionCode.INVALID_TOKEN);
//        }
        if(createTokenDTO.getRefreshToken().isEmpty()){
            throw new NotFoundRefreshTokenException(ExceptionCode.NOT_FOUND_TOKEN);
        }
        String refreshToken = createTokenDTO.getRefreshToken();

//        String accessToken = authHeader.substring(7);
//
//        //고의로 직접 /api/member/refresh 를 호출할 수도 있으니 체크
//        if(checkExpiredToken(accessToken) == false){
//            String result = "accessToken이 만료되지 않았습니다.";
//            return Map.of("accessToken",accessToken, "refreshToken",refreshToken, "result",result);
//        }

        Map<String, Object> claims = JwtProvider.validateToken(refreshToken);
        String userId = claims.get("userId").toString();
        String key= REFRESH_TOKEN_PREFIX+userId;

        String redisRefreshToken = redisTemplate.opsForValue().get(key);

        String newAccessToken = JwtProvider.generateToken(claims,ACCESS_TOKEN_EXPIRATION);

        //refreshToken 기간이 60분 이하로 남았을 경우 새로운 refreshToken을 발급
        //아닐 경우 기존 refreshToken을 사용한다
        String newReToken;
        if(checkTime((Integer)claims.get("exp"))){
            newReToken=  JwtProvider.generateToken(claims,REFRESH_TOKEN_EXPIRATION);
        }else {
            newReToken = redisRefreshToken;
        }

        return Map.of("accessToken",newAccessToken,"refreshToken",newReToken);
    }
    private boolean checkTime(Integer exp) {

        //exp를 날짜로 변환
        Date expDate = new Date((long) exp * 1000);
        //현재 시간과의 차이 계산
        long gap = expDate.getTime() - System.currentTimeMillis();
        //분단위 계산
        long leftMin = gap / 60000;

        return leftMin < REFRESH_TOKEN_RENEW_THRESHOLD;
    }
    private boolean checkExpiredToken(String accessToken) {
        try {
            JwtProvider.validateToken(accessToken);
        }catch (CustomJWTException e){
            if(e.getExceptionCode().getCode().equals(ExceptionCode.EXPIRED_TOKEN.getCode())){
                log.info("=============Expired Token=============");
                return true;
            }else {
                throw e;
            }
        }
        return false;
    }
}
