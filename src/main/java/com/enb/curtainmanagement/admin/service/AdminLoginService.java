package com.enb.curtainmanagement.admin.service;

import com.enb.curtainmanagement.auth.model.Token;
import com.enb.curtainmanagement.auth.model.dto.request.LoginRequest;

public interface AdminLoginService {

    Token login(final LoginRequest loginRequest);

}
