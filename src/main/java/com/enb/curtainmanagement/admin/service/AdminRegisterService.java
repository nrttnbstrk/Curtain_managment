package com.enb.curtainmanagement.admin.service;

import com.enb.curtainmanagement.admin.model.Admin;
import com.enb.curtainmanagement.admin.model.dto.request.AdminRegisterRequest;

public interface AdminRegisterService {

    Admin registerAdmin(final AdminRegisterRequest adminRegisterRequest);

}
