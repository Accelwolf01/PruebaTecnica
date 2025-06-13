package com.users.login.config;

import com.users.login.service.TokenService;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private final TokenService tokenService;

    public FeignConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Bean
    public RequestInterceptor feignInterceptor() {
        return requestTemplate -> {
            String token = tokenService.obtenerToken();
            if (token != null && !token.isEmpty()) {
                requestTemplate.header("Authorization", "Bearer " + token);
            }
        };
    }
}
