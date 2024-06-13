package com.eliceteam8.edupay.security.config.jwt;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.eliceteam8.edupay.global.exception.CustomJWTException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j

@RequiredArgsConstructor
public class JwtProvider {

    private final RedisTemplate<String, String> redisTemplate;

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private static final long TOKEN_EXPIRATION_MINUTES = 60; // 토큰 만료 시간

    private static final long EXPIRATION_THRESHOLD = 600L;
    private static final long TOKEN_EXPIRATION_HOURS = 12L;

    public String generateRefreshToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String token = base64Encoder.encodeToString(randomBytes);
        redisTemplate.opsForValue().set(token, "valid", TOKEN_EXPIRATION_MINUTES, TimeUnit.MINUTES);
        return token;
    }

    public boolean validateRefreshToken(String token) {
        return redisTemplate.hasKey(token);
    }

    public void deleteRefreshToken(String token) {
        redisTemplate.delete(token);
    }


    public boolean refreshTokenSave( String refreshToken, String userId){
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
