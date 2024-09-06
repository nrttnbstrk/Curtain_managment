package com.enb.curtainmanagement.admin.service.impl;

import com.enb.curtainmanagement.admin.exception.AdminAlreadyExistException;
import com.enb.curtainmanagement.admin.repository.AdminRepository;
import com.enb.curtainmanagement.admin.model.Admin;
import com.enb.curtainmanagement.admin.model.dto.request.AdminRegisterRequest;
import com.enb.curtainmanagement.admin.model.entity.AdminEntity;
import com.enb.curtainmanagement.admin.model.mapper.AdminEntityToAdminMapper;
import com.enb.curtainmanagement.admin.model.mapper.AdminRegisterRequestToAdminEntityMapper;
import com.enb.curtainmanagement.admin.service.AdminRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminRegisterServiceImpl implements AdminRegisterService {

    private final AdminRepository adminRepository;
    private final AdminRegisterRequestToAdminEntityMapper adminRegisterRequestToAdminEntityMapper = AdminRegisterRequestToAdminEntityMapper.initialize();

    private final AdminEntityToAdminMapper adminEntityToAdminMapper = AdminEntityToAdminMapper.initialize();

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Admin registerAdmin(AdminRegisterRequest adminRegisterRequest) {

        if (adminRepository.existsAdminEntityByEmail(adminRegisterRequest.getEmail())) {
            throw new AdminAlreadyExistException("Bu mail adresi zaten kullanılmakta.");
        }

        final AdminEntity adminEntityToBeSave = adminRegisterRequestToAdminEntityMapper.mapForSaving(adminRegisterRequest);

        adminEntityToBeSave.setPassword(passwordEncoder.encode(adminRegisterRequest.getPassword()));

        AdminEntity savedAdminEntity = adminRepository.save(adminEntityToBeSave);

        return adminEntityToAdminMapper.map(savedAdminEntity);

    }

}
