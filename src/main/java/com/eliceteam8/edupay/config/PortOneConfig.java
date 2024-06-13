package com.eliceteam8.edupay.config;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortOneConfig {

    @Value("${imp.api.key}")
    private String apiKey;
    @Value("${imp.api.secret_key}")
    private String secretKey;

    @Bean
    public IamportClient iamportClient() {
        System.out.println("apiKey = " + apiKey);
        System.out.println("secretKey = " + secretKey);
        return new IamportClient(apiKey, secretKey);
    }
}

