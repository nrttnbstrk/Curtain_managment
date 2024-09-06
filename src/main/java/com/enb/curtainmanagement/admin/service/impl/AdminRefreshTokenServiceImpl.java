package com.enb.curtainmanagement.admin.service.impl;

import com.enb.curtainmanagement.admin.exception.AdminNotFoundException;
import com.enb.curtainmanagement.admin.repository.AdminRepository;
import com.enb.curtainmanagement.auth.model.Token;
import com.enb.curtainmanagement.auth.model.dto.request.TokenRefreshRequest;
import com.enb.curtainmanagement.admin.model.entity.AdminEntity;
import com.enb.curtainmanagement.admin.service.AdminRefreshTokenService;
import com.enb.curtainmanagement.auth.exception.UserStatusNotValidException;
import com.enb.curtainmanagement.auth.model.enums.TokenClaims;
import com.enb.curtainmanagement.auth.model.enums.UserStatus;
import com.enb.curtainmanagement.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminRefreshTokenServiceImpl implements AdminRefreshTokenService {

    private final AdminRepository adminRepository;
    private final TokenService tokenService;

    @Override
    public Token refreshToken(TokenRefreshRequest tokenRefreshRequest) {

        tokenService.verifyAndValidate(tokenRefreshRequest.getRefreshToken());

        final String adminId = tokenService
                .getPayload(tokenRefreshRequest.getRefreshToken())
                .get(TokenClaims.USER_ID.getValue())
                .toString();

        final AdminEntity adminEntityFromDB = adminRepository
                .findById(adminId)
                .orElseThrow(AdminNotFoundException::new);

        this.validateAdminStatus(adminEntityFromDB);

        return tokenService.generateToken(
                adminEntityFromDB.getClaims(),
                tokenRefreshRequest.getRefreshToken()
        );

    }

    private void validateAdminStatus(final AdminEntity adminEntity) {
        if (!(UserStatus.ACTIVE.equals(adminEntity.getUserStatus()))) {
            throw new UserStatusNotValidException("UserStatus = " + adminEntity.getUserStatus());
        }
    }

}
