package com.enb.curtainmanagement.admin.service.impl;

import com.enb.curtainmanagement.admin.exception.AdminNotFoundException;
import com.enb.curtainmanagement.admin.repository.AdminRepository;
import com.enb.curtainmanagement.auth.model.Token;
import com.enb.curtainmanagement.auth.model.dto.request.LoginRequest;
import com.enb.curtainmanagement.admin.model.entity.AdminEntity;
import com.enb.curtainmanagement.admin.service.AdminLoginService;
import com.enb.curtainmanagement.auth.exception.PasswordNotValidException;
import com.enb.curtainmanagement.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLoginServiceImpl implements AdminLoginService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Override
    public Token login(LoginRequest loginRequest) {

        final AdminEntity adminEntityFromDB = adminRepository
                .findAdminEntityByEmail(loginRequest.getEmail())
                .orElseThrow(
                        () -> new AdminNotFoundException("Giriş yapılan E-posta Kayıtlı Değil. "));

        if (Boolean.FALSE.equals(passwordEncoder.matches(
                loginRequest.getPassword(), adminEntityFromDB.getPassword()))) {
            throw new PasswordNotValidException();
        }

        return tokenService.generateToken(adminEntityFromDB.getClaims());

    }

}
