package com.enb.curtainmanagement.admin.controller;

import com.enb.curtainmanagement.auth.model.Token;
import com.enb.curtainmanagement.auth.model.dto.request.LoginRequest;
import com.enb.curtainmanagement.auth.model.dto.request.TokenInvalidateRequest;
import com.enb.curtainmanagement.auth.model.dto.request.TokenRefreshRequest;
import com.enb.curtainmanagement.common.model.dto.response.CustomResponse;
import com.enb.curtainmanagement.admin.model.dto.request.AdminRegisterRequest;
import com.enb.curtainmanagement.admin.service.AdminLoginService;
import com.enb.curtainmanagement.admin.service.AdminLogoutService;
import com.enb.curtainmanagement.admin.service.AdminRefreshTokenService;
import com.enb.curtainmanagement.admin.service.AdminRegisterService;
import com.enb.curtainmanagement.auth.model.dto.response.TokenResponse;
import com.enb.curtainmanagement.auth.model.mapper.TokenToTokenResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication/admin")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminRegisterService adminRegisterService;
    private final AdminLoginService adminLoginService;
    private final AdminRefreshTokenService adminRefreshTokenService;
    private final AdminLogoutService adminLogoutService;

    private TokenToTokenResponseMapper tokenToTokenResponseMapper = TokenToTokenResponseMapper.initialize();

    @PostMapping("/register")
    public CustomResponse<Void> registerAdmin(@RequestBody @Valid final AdminRegisterRequest adminRegisterRequest) {
        adminRegisterService.registerAdmin(adminRegisterRequest);
        return CustomResponse.SUCCESS;
    }

    @PostMapping("/login")
    public CustomResponse<TokenResponse> loginAdmin(@RequestBody @Valid final LoginRequest loginRequest) {
        final Token token = adminLoginService.login(loginRequest);
        final TokenResponse tokenResponse = tokenToTokenResponseMapper.map(token);
        return CustomResponse.successOf(tokenResponse);
    }

    @PostMapping("/refresh-token")
    public CustomResponse<TokenResponse> refreshToken(@RequestBody @Valid final TokenRefreshRequest tokenRefreshRequest) {
        final Token token = adminRefreshTokenService.refreshToken(tokenRefreshRequest);
        final TokenResponse tokenResponse = tokenToTokenResponseMapper.map(token);
        return CustomResponse.successOf(tokenResponse);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<Void> logout(@RequestBody @Valid final TokenInvalidateRequest tokenInvalidateRequest) {
        adminLogoutService.logout(tokenInvalidateRequest);
        return CustomResponse.SUCCESS;
    }

}
