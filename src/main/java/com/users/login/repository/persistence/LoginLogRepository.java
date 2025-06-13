package com.users.login.repository.persistence;

import com.users.login.repository.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginLogRepository extends JpaRepository<LoginLog, UUID> {

}
