package com.eliceteam8.edupay.security.config.jwt;

import com.eliceteam8.edupay.global.enums.ErrorCode;
import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.eliceteam8.edupay.global.exception.CustomJWTException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JwtProvider {


    public static String generateToken(Map<String,Object> valueMap, int min){
        SecretKey key = null;

        try {
            //hmaShaKeyFor : HMAC-SHA key를 생성하는 메소드 jjwt 라이브러리에서 제공
            key = Keys.hmacShaKeyFor(JWTProperties.getSecretKey().getBytes("UTF-8"));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        Date now = new Date();
        String jwtStr = Jwts.builder()
                .setHeaderParam("typ","JWT")
                //.setIssuer()
                .setClaims(valueMap)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(min).toMillis()))
               // .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
               // .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(key)
                .compact();

        return jwtStr;
    }

    public static Map<String,Object> validateToken(String token){
        Map<String,Object> claims = null;

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(JWTProperties.getSecretKey().getBytes("UTF-8")))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch(MalformedJwtException malformedJwtException){
            throw new CustomJWTException(ExceptionCode.MALFORM_TOKEN);
        }catch(ExpiredJwtException expiredJwtException){
            throw new CustomJWTException(ExceptionCode.EXPIRED_TOKEN);
        }catch(InvalidClaimException invalidClaimException){
            throw new CustomJWTException(ExceptionCode.INVALID_TOKEN);
        }catch(JwtException jwtException){
            throw new CustomJWTException(ExceptionCode.INVALID_SIGNATURE);
        }catch(Exception e){
            throw new CustomJWTException(ExceptionCode.ERROR_TOKEN);
        }
        return claims;
    }

}
