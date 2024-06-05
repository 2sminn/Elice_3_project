package com.eliceteam8.edupay.security.filter;

import com.eliceteam8.edupay.global.exception.CustomJWTException;
import com.eliceteam8.edupay.security.config.jwt.JwtProvider;
import com.eliceteam8.edupay.user.dto.UserDTO;
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


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        if(path.startsWith("/auth/")){
            return true;
        }
        if(path.startsWith("/token/")){
            return true;
        }


        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("---------JWTCheckFilter doFilterInternal----------");


        String authHeaderStr =  request.getHeader("Authorization");

        try {
            String accessToken = authHeaderStr.substring(7);

            Map<String, Object> claims = JwtProvider.validateToken(accessToken);

            String email = (String) claims.get("email");
            String password = (String) claims.get("password");
            List<String> roles = (List<String>) claims.get("roles");
            Long academyId = ((Integer) claims.get("academyId")).longValue();
            Long userId = ((Integer) claims.get("userId")).longValue();


            UserDTO userDTO = new UserDTO(email, password, roles, academyId, userId);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDTO, password, userDTO.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            filterChain.doFilter(request, response);

        }catch (CustomJWTException e){
            log.error("JWTCheckFilter error : {}");
            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("status",e.getExceptionCode().getStatus(),
                    "message",e.getExceptionCode().getMessage(),
                    "code",e.getExceptionCode().getCode()));

            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println(msg);


        }catch (Exception e){
            log.error("JWTCheckFilter Exception : {}", e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error","ERROR_ACCESS_TOKEN"));

            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println(msg);
        }
    }
}
