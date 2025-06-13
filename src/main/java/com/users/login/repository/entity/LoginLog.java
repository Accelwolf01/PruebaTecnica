package com.users.login.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "login_log")
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private LocalDateTime loginTime;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String accessToken;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String refreshToken;
}
