package com.eliceteam8.edupay.security.config.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTProperties {

    private static String secretKey;


    @Value("${jwt.secretKey}")
    public void setSecretKey(String secretKey) {
        JWTProperties.secretKey = secretKey;
    }

    public static String getSecretKey() {
        return secretKey;
    }

}
