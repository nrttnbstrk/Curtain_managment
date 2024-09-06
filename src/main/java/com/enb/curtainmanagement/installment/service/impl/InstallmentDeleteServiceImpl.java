package com.enb.curtainmanagement.installment.service.impl;

import com.enb.curtainmanagement.installment.exception.InstallmentNotFoundException;
import com.enb.curtainmanagement.installment.repository.InstallmentRepository;
import com.enb.curtainmanagement.installment.model.entity.InstallmentEntity;
import com.enb.curtainmanagement.installment.service.InstallmentDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstallmentDeleteServiceImpl implements InstallmentDeleteService {

    private final InstallmentRepository installmentRepository;

    @Override
    public void deleteInstallmentById(String installmentId) {

        final InstallmentEntity installmentEntityToBeDelete = installmentRepository
                .findById(installmentId)
                .orElseThrow(() -> new InstallmentNotFoundException("Silmek istediğiniz Taksıt mevcut değil."));

        installmentRepository.delete(installmentEntityToBeDelete);

    }

}
