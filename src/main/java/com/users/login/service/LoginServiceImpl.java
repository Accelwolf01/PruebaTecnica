package com.users.login.service;

import com.users.login.dto.Usuario;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.users.login.client.AuthClient;
import com.users.login.repository.entity.LoginLog;
import com.users.login.repository.persistence.LoginLogRepository;
import com.users.login.dto.LoginRequest;
import com.users.login.dto.LoginResponse;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private final AuthClient authClient;
    @Autowired
    private final LoginLogRepository loginLogRepository;

    public LoginServiceImpl(AuthClient authClient, LoginLogRepository loginLogRepository) {
        this.authClient = authClient;
        this.loginLogRepository = loginLogRepository;
    }

    @Override
    public LoginResponse autenticar(LoginRequest request) {
        try{
            LoginResponse response = authClient.login(request);
            // Registrar login exitoso en la base de datos
            LoginLog log = new LoginLog();
            log.setUsername(request.getUsername());
            log.setLoginTime(LocalDateTime.now());
            log.setAccessToken(response.getAccessToken());
            log.setRefreshToken(response.getRefreshToken());

            loginLogRepository.save(log);
            return response;
        }catch(FeignException e){
            LoginResponse response = new LoginResponse();
            String errorMessage = e.contentUTF8().contains("\"message\"") ?
                    e.contentUTF8().split("\"message\":\"")[1].split("\"")[0] :
                    "Error en autenticación";
            response.setAccessToken(errorMessage);
            return response;
        }
    }

    @Override
    public Usuario obtenerUsuario(String token) {
        return authClient.obtenerUsuario(token);
    }
}
