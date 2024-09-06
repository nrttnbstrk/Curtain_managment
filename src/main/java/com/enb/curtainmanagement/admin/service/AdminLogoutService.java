package com.enb.curtainmanagement.admin.service;

import com.enb.curtainmanagement.auth.model.dto.request.TokenInvalidateRequest;

public interface AdminLogoutService {

    void logout(final TokenInvalidateRequest tokenInvalidateRequest);

}
