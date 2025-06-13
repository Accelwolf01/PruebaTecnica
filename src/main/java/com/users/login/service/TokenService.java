package com.users.login.service;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class TokenService {
    private String accessToken;

    public void guardarToken(String token) {
        this.accessToken = token;
    }

    public String obtenerToken() {
        return accessToken;
    }
}
