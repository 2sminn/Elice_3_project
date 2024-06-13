package com.eliceteam8.edupay.security.config.jwt;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.eliceteam8.edupay.global.exception.CustomJWTException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final RedisTemplate<String, String> redisTemplate;
    private static final long TOKEN_EXPIRATION_HOURS = 12L;
    private static final String REFRESH_TOKEN_PREFIX = "refreshToken_";


    public boolean refreshTokenSave(String refreshToken, String userId) {
        try {
            String key = REFRESH_TOKEN_PREFIX + userId;
            redisTemplate.opsForValue().set(
                    key,
                    refreshToken,
                    Duration.ofHours(TOKEN_EXPIRATION_HOURS));
            return true;
        } catch (Exception e) {
            log.error("Error saving refresh token to Redis :  {}", e.getMessage());
            return false;
        }
    }

    public static String generateToken(Map<String,Object> valueMap, int min){
        SecretKey key = null;
        String jwtStr = null;

        try {
            //hmaShaKeyFor : HMAC-SHA key를 생성하는 메소드 jjwt 라이브러리에서 제공
            key = Keys.hmacShaKeyFor(JWTProperties.getSecretKey().getBytes("UTF-8"));
            Date now = new Date();
            jwtStr= Jwts.builder()
                    .setHeaderParam("typ","JWT")
                    //.setIssuer()
                    .setClaims(valueMap)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + Duration.ofMinutes(min).toMillis()))
                    // .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                    // .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                    .signWith(key)
                    .compact();
        }catch (Exception e){
            throw new CustomJWTException(ExceptionCode.TOKEN_CREATION_ERROR);
        }

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
