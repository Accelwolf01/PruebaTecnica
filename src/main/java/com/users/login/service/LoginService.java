package com.users.login.service;

import com.users.login.dto.LoginRequest;
import com.users.login.dto.LoginResponse;
import com.users.login.dto.Usuario;

public interface LoginService {
    public LoginResponse autenticar(LoginRequest request);
    public Usuario obtenerUsuario(String token);
}
