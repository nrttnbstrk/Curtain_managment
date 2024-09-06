package com.enb.curtainmanagement.installment.service.impl;

import com.enb.curtainmanagement.installment.exception.InstallmentAlreadyExistException;
import com.enb.curtainmanagement.installment.repository.InstallmentRepository;
import com.enb.curtainmanagement.installment.model.Installment;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentCreateRequest;
import com.enb.curtainmanagement.installment.model.entity.InstallmentEntity;
import com.enb.curtainmanagement.installment.model.mapper.InstallmentCreateRequestToInstallmentEntityMapper;
import com.enb.curtainmanagement.installment.model.mapper.InstallmentEntityToInstallmentMapper;
import com.enb.curtainmanagement.installment.service.InstallmentCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstallmentCreateServiceImpl implements InstallmentCreateService {

    private final InstallmentRepository installmentRepository;

    private final InstallmentCreateRequestToInstallmentEntityMapper installmentCreateRequestToInstallmentEntityMapper =
            InstallmentCreateRequestToInstallmentEntityMapper.initialize();

    private final InstallmentEntityToInstallmentMapper installmentEntityToInstallmentMapper = InstallmentEntityToInstallmentMapper.initialize();

    @Override
    public Installment createInstallment(InstallmentCreateRequest installmentCreateRequest) {


        final InstallmentEntity installmentEntityToBeSave = installmentCreateRequestToInstallmentEntityMapper.mapForSaving(installmentCreateRequest);

        InstallmentEntity savedInstallmentEntity = installmentRepository.save(installmentEntityToBeSave);

        return installmentEntityToInstallmentMapper.map(savedInstallmentEntity);

    }


}
