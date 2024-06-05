package com.eliceteam8.edupay.security.config;


import com.eliceteam8.edupay.security.filter.JWTCheckFilter;
import com.eliceteam8.edupay.security.handler.CustomAccessDeniedHandler;
import com.eliceteam8.edupay.security.handler.LoginFailHandler;
import com.eliceteam8.edupay.security.handler.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityCustomConfig {

    private final RedisTemplate<String, String> redisTemplate;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });

        http.sessionManagement(sessionManagement -> {
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.csrf(config -> {
            config.disable();
        });



        http
                .formLogin(formLogin ->
                        formLogin
                                .usernameParameter("email")
                                .loginPage("/auth/login")
                                .successHandler(new LoginSuccessHandler(redisTemplate))
                                .failureHandler(new LoginFailHandler())
                );


        http.addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class);
      //  http.addFilterAfter(new JWTAuthHandlerFilter(), JWTCheckFilter.class);

        http.exceptionHandling(config->{
            config.accessDeniedHandler(new CustomAccessDeniedHandler());
        });
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS","HEAD"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type","Cache-Control"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
