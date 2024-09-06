package com.enb.curtainmanagement.installment.service.impl;

import com.enb.curtainmanagement.installment.exception.InstallmentNotFoundException;
import com.enb.curtainmanagement.installment.repository.InstallmentRepository;
import com.enb.curtainmanagement.installment.specification.InstallmentSpecification;
import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.installment.model.Installment;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentPagingRequest;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentSearchRequest;
import com.enb.curtainmanagement.installment.model.dto.response.InstallmentSearchResponse;
import com.enb.curtainmanagement.installment.model.entity.InstallmentEntity;
import com.enb.curtainmanagement.installment.model.mapper.ListInstallmentEntityToListInstallmentMapper;
import com.enb.curtainmanagement.installment.model.mapper.InstallmentEntityToInstallmentMapper;
import com.enb.curtainmanagement.installment.service.InstallmentReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstallmentReadServiceImpl implements InstallmentReadService {

    private final InstallmentRepository installmentRepository;
    public boolean doesCustomerExistByIdNumber(String installmentId) {
        return installmentRepository.existsInstallmentEntitiesById(installmentId);
    }
    private final InstallmentEntityToInstallmentMapper installmentEntityToInstallmentMapper = InstallmentEntityToInstallmentMapper.initialize();

    private final ListInstallmentEntityToListInstallmentMapper installmentEntityToListInstallmentMapper =
            ListInstallmentEntityToListInstallmentMapper.initialize();

    @Override
    public Installment getInstallmentById(String installmentId) {

        final InstallmentEntity installmentEntityFromDB = installmentRepository
                .findById(installmentId)
                .orElseThrow(() -> new InstallmentNotFoundException("Belirtilen Taksit mevcut değil."));

        return installmentEntityToInstallmentMapper.map(installmentEntityFromDB);
    }

    @Override
    public CustomPage<Installment> getInstallments(InstallmentPagingRequest installmentPagingRequest) {

        final Page<InstallmentEntity> installmentEntityPage = installmentRepository.findAll(installmentPagingRequest.toPageable());

        if (installmentEntityPage.getContent().isEmpty()) {
            throw new InstallmentNotFoundException("Herhangi bir taksit bulunamadı.");
        }

        final List<Installment> installmentDomainModels = installmentEntityToListInstallmentMapper
                .toInstallmentList(installmentEntityPage.getContent());

        return CustomPage.of(installmentDomainModels, installmentEntityPage);

    }


    @Override
    public List<InstallmentSearchResponse> searchInstallments(InstallmentSearchRequest searchRequest) {
        Specification<InstallmentEntity> specification = Specification.where(null);

        if (searchRequest.getCreatedDate() != null) {
            specification = specification.and(InstallmentSpecification.hasCreatedDate(searchRequest.getCreatedDate()));
        }
        if (searchRequest.getCustomerId() != null && !searchRequest.getCustomerId().isEmpty()) {
            specification = specification.and(InstallmentSpecification.hasCustomerId(searchRequest.getCustomerId()));
        }
        if (searchRequest.getInstallmentDate() != null) {
            specification = specification.and(InstallmentSpecification.hasInstallmentDate(searchRequest.getInstallmentDate()));
        }
        if (searchRequest.getInstallmentPrice() != null) {
            specification = specification.and(InstallmentSpecification.hasInstallmentPrice(searchRequest.getInstallmentPrice()));
        }
        if (searchRequest.getInstallmentWhich() != null && !searchRequest.getInstallmentWhich().isEmpty()) {
            specification = specification.and(InstallmentSpecification.hasInstallmentWhich(searchRequest.getInstallmentWhich()));
        }
        if (searchRequest.getSaleId() != null && !searchRequest.getSaleId().isEmpty()) {
            specification = specification.and(InstallmentSpecification.hasSaleId(searchRequest.getSaleId()));
        }
        if (searchRequest.getStatus() != null && !searchRequest.getStatus().isEmpty()) {
            specification = specification.and(InstallmentSpecification.hasStatus(searchRequest.getStatus()));
        }
        if (searchRequest.getTotalPrice() != null) {
            specification = specification.and(InstallmentSpecification.hasTotalPrice(searchRequest.getTotalPrice()));
        }
        if (searchRequest.getInstallmentQuantity() != 0) {
            specification = specification.and(InstallmentSpecification.hasInstallmentQuantity(searchRequest.getInstallmentQuantity()));
        }

        List<InstallmentEntity> installmentEntities = installmentRepository.findAll(specification);

        return installmentEntities.stream().map(this::mapToResponse).collect(Collectors.toList());
    }



    private InstallmentSearchResponse mapToResponse(InstallmentEntity entity) {
        InstallmentSearchResponse response = new InstallmentSearchResponse();
        response.setId(entity.getId());
        response.setCreatedDate(entity.getCreatedDate());
        response.setCustomerId(entity.getCustomerId());
        response.setInstallmentDate(entity.getInstallmentDate());
        response.setInstallmentPrice(entity.getInstallmentPrice());
        response.setInstallmentWhich(entity.getInstallmentWhich());
        response.setSaleId(entity.getSaleId());
        response.setStatus(entity.getStatus());
        response.setTotalPrice(entity.getTotalPrice());
        response.setInstallmentQuantity(entity.getInstallmentQuantity());
        return response;
    }

}
