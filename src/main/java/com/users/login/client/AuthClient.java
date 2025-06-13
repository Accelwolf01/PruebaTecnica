package com.users.login.client;

import com.users.login.config.FeignConfig;
import com.users.login.dto.LoginRequest;
import com.users.login.dto.LoginResponse;
import com.users.login.dto.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "dummyJsonAuth", url = "https://dummyjson.com", configuration = FeignConfig.class)
public interface AuthClient {

    @PostMapping("/auth/login")
    LoginResponse login(@RequestBody LoginRequest request);

    @GetMapping("/auth/me")
    Usuario obtenerUsuario(@RequestHeader("Authorization") String token);
}
