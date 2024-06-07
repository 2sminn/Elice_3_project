package com.eliceteam8.edupay.security.handler;

import com.eliceteam8.edupay.global.enums.ExceptionCode;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class LoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        log.info("--- LoginFailHandler --- ");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(Map.of("status", ExceptionCode.LOGIN_FAILED.getStatus(),
                "messageDetail", ExceptionCode.LOGIN_FAILED.getMessage(),
                "message", exception.getMessage(),
                "code",ExceptionCode.LOGIN_FAILED.getCode()));

        response.getWriter().println(jsonStr);

    }
}
