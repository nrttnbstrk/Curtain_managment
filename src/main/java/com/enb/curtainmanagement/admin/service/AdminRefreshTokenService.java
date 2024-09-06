package com.enb.curtainmanagement.admin.service;

import com.enb.curtainmanagement.auth.model.Token;
import com.enb.curtainmanagement.auth.model.dto.request.TokenRefreshRequest;

public interface AdminRefreshTokenService {

    Token refreshToken(final TokenRefreshRequest tokenRefreshRequest);

}
