package com.enb.curtainmanagement.installment.service.impl;

import com.enb.curtainmanagement.installment.exception.InstallmentAlreadyExistException;
import com.enb.curtainmanagement.installment.exception.InstallmentNotFoundException;
import com.enb.curtainmanagement.installment.repository.InstallmentRepository;
import com.enb.curtainmanagement.installment.model.Installment;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentUpdateRequest;
import com.enb.curtainmanagement.installment.model.entity.InstallmentEntity;
import com.enb.curtainmanagement.installment.model.mapper.InstallmentEntityToInstallmentMapper;
import com.enb.curtainmanagement.installment.model.mapper.InstallmentUpdateRequestToInstallmentEntityMapper;
import com.enb.curtainmanagement.installment.service.InstallmentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstallmentUpdateServiceImpl implements InstallmentUpdateService {

    private final InstallmentRepository installmentRepository;

    private final InstallmentUpdateRequestToInstallmentEntityMapper installmentUpdateRequestToInstallmentEntityMapper =
            InstallmentUpdateRequestToInstallmentEntityMapper.initialize();

    private final InstallmentEntityToInstallmentMapper installmentEntityToInstallmentMapper =
            InstallmentEntityToInstallmentMapper.initialize();

    @Override
    public Installment updateInstallmentById(String installmentId, InstallmentUpdateRequest installmentUpdateRequest) {


        final InstallmentEntity installmentEntityToBeUpdate = installmentRepository
                .findById(installmentId)
                .orElseThrow(() -> new InstallmentNotFoundException("Belirtilen taksit mevcut değil." ));

        installmentUpdateRequestToInstallmentEntityMapper.mapForUpdating(installmentEntityToBeUpdate, installmentUpdateRequest);

        InstallmentEntity updatedInstallmentEntity = installmentRepository.save(installmentEntityToBeUpdate);

        return installmentEntityToInstallmentMapper.map(updatedInstallmentEntity);

    }



}
