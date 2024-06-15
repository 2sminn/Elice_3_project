package com.eliceteam8.edupay.security.filter;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.eliceteam8.edupay.global.exception.CustomJWTException;
import com.eliceteam8.edupay.global.response.ErrorResponse;
import com.eliceteam8.edupay.security.config.jwt.JwtProvider;
import com.eliceteam8.edupay.user.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class JWTCheckFilter extends OncePerRequestFilter {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        if(path.startsWith("/auth/")){
            return true;
        }
        if(path.startsWith("/token/")){
            return true;
        }

        //test
        if(path.startsWith("/bill/")){
            return true;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("---------JWTCheckFilter doFilterInternal----------");
        try {
            String authHeaderStr =  request.getHeader("Authorization");

            if(authHeaderStr == null || !authHeaderStr.startsWith("Bearer ")){
                throw new CustomJWTException(ExceptionCode.NOT_FOUND_TOKEN);
            }
            String accessToken = authHeaderStr.substring(7);

            Map<String, Object> claims = JwtProvider.validateToken(accessToken);

            String email = (String) claims.get("email");
            List<String> roles = (List<String>) claims.get("roles");
            Long academyId = ((Integer) claims.get("academyId")).longValue();
            Long userId = ((Integer) claims.get("userId")).longValue();


            UserDTO userDTO = new UserDTO(email, "", roles, academyId, userId);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDTO, "", userDTO.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            filterChain.doFilter(request, response);

        }catch (CustomJWTException e) {
            log.error("JWTCheckFilter error ");
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ErrorResponse errorResponse = ErrorResponse.of(e.getExceptionCode());
            response.getWriter().println(objectMapper.writeValueAsString(errorResponse));

        }

    }


}
