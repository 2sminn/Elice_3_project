package com.eliceteam8.edupay.security.filter;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.eliceteam8.edupay.global.exception.CustomJWTException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JWTAuthHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("---------JWTAuthHandlerFilter doFilterInternal----------");

        try{
            filterChain.doFilter(request, response);
        }catch(MalformedJwtException malformedJwtException){
            new CustomJWTException(ExceptionCode.MALFORM_TOKEN);
        }catch(ExpiredJwtException expiredJwtException){
            throw new CustomJWTException(ExceptionCode.EXPIRED_TOKEN);
        }catch(InvalidClaimException invalidClaimException){
            new CustomJWTException(ExceptionCode.INVALID_TOKEN);
        }catch(JwtException jwtException){
            new CustomJWTException(ExceptionCode.INVALID_SIGNATURE);
        }catch(Exception e){
            // throw new CustomJWTException("Error");
        }
    }
}
