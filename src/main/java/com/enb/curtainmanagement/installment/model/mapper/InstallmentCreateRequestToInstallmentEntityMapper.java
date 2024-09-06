package com.enb.curtainmanagement.installment.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentCreateRequest;
import com.enb.curtainmanagement.installment.model.entity.InstallmentEntity;
import jakarta.persistence.Column;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDate;

@Mapper
public interface InstallmentCreateRequestToInstallmentEntityMapper extends BaseMapper<InstallmentCreateRequest, InstallmentEntity> {

    @Named("mapForSaving")
    default InstallmentEntity mapForSaving(InstallmentCreateRequest installmentCreateRequest) {
        return InstallmentEntity.builder()
                .createdDate(installmentCreateRequest.getCreatedDate())
                .installmentDate(installmentCreateRequest.getInstallmentDate())
                .customerId(installmentCreateRequest.getCustomerId()) // String
                .installmentPrice(installmentCreateRequest.getInstallmentPrice()) // BigDecimal
                .installmentWhich(installmentCreateRequest.getInstallmentWhich()) // String
                .saleId(installmentCreateRequest.getSaleId()) // String
                .status(installmentCreateRequest.getStatus()) // String
                .totalPrice(installmentCreateRequest.getTotalPrice()) // BigDecimal
                .installmentQuantity(installmentCreateRequest.getInstallmentQuantity()) // int
                .build();
    }



    static InstallmentCreateRequestToInstallmentEntityMapper initialize() {
        return Mappers.getMapper(InstallmentCreateRequestToInstallmentEntityMapper.class);
    }

}
