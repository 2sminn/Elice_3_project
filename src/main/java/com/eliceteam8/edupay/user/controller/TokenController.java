package com.eliceteam8.edupay.user.controller;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.eliceteam8.edupay.global.exception.CustomJWTException;
import com.eliceteam8.edupay.security.config.jwt.JwtProvider;
import com.eliceteam8.edupay.user.entity.RefreshToken;
import com.eliceteam8.edupay.user.entity.User;
import com.eliceteam8.edupay.user.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TokenController {
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;

    @RequestMapping("/token/refresh")
    public Map<String,Object> refresh(@RequestHeader("Authorization")String authHeader, String email){


        if(authHeader == null || authHeader.length() < 7){
            throw new CustomJWTException(ExceptionCode.INVALID_TOKEN);
        }
        if(email == null){
            throw new IllegalArgumentException("email 값이 없습니다.");
        }

        String accessToken = authHeader.substring(7);

        //accessToken 만료되지 않았다면
        //accessToken 만료되어 호출한건데 체크를 또?  ,고의로 직접 /api/member/refresh 를 호출할 수도 있으니 체크
        if(checkExpiredToken(accessToken) == false){
            String result = "accessToken 만료되지 않았습니다.";
            return Map.of("accessToken",accessToken,"result",result);
        }


//        Map<String, Object> accessTokenClaims = JwtProvider.validateToken(accessToken);
//        String email = (String) accessTokenClaims.get("email");
//        if(email.equals(refreshToken.getEmail()) == false){
//            throw new CustomJWTException(ExceptionCode.INVALID_TOKEN);
//        }
//        User user = userRepository.findUserByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Not Found User"));


        String redisRefreshToken = redisTemplate.opsForValue().get(email);
        if(redisRefreshToken == null){
            throw new UsernameNotFoundException("Not Found RefreshToken");
        }


        Map<String, Object> claims = JwtProvider.validateToken(redisRefreshToken);
        if(email.equals(claims.get("email")) == false){
            throw new UsernameNotFoundException("유저 정보가 토큰값이 일치하지 않습니다.");
        }

        String newAccessToken = JwtProvider.generateToken(claims,10);
        //String newRefreshToken = checkTime((Integer)claims.get("exp")) == true? JwtProvider.generateToken(claims,60*12):redisRefreshToken;

        String newReToken;
        if(checkTime((Integer)claims.get("exp"))){
            newReToken=  JwtProvider.generateToken(claims,60*12);
            redisTemplate.opsForValue().set(email,newReToken);
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

        return leftMin < 60;
    }
    private boolean checkExpiredToken(String accessToken) {
        try {
            JwtProvider.validateToken(accessToken);
        }catch (CustomJWTException e){
            if(e.getExceptionCode().getCode().equals(ExceptionCode.EXPIRED_TOKEN.getCode())){
                log.info("=============Expired Token=============");
                return true;
            }
        }
        return false;
    }
}
