package com.eliceteam8.edupay.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.ZonedDateTime;
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

        String jwtStr = Jwts.builder()
                .setHeaderParam("typ","JWT")
                //.setIssuer()
                .setClaims(valueMap)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
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
            log.error("MalFormed");
        }catch(ExpiredJwtException expiredJwtException){
            log.error("Expired");
        }catch(InvalidClaimException invalidClaimException){
            log.error("Invalid");
        }catch(JwtException jwtException){
            log.error("JWTError");
        }catch(Exception e){
            log.error("Error");
        }
        return claims;
    }

}
