package com.users.login.controller;

import com.users.login.dto.LoginRequest;
import com.users.login.dto.LoginResponse;
import com.users.login.dto.Usuario;
import com.users.login.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginService.autenticar(request));
    }

    @GetMapping("/me")
    public ResponseEntity<Usuario> obtenerUsuario(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(loginService.obtenerUsuario(token));
    }
}
