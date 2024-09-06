package com.enb.curtainmanagement.installment.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentUpdateRequest;
import com.enb.curtainmanagement.installment.model.entity.InstallmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InstallmentUpdateRequestToInstallmentEntityMapper extends BaseMapper<InstallmentUpdateRequest, InstallmentEntity> {


    @Named("mapForUpdating")
    default void mapForUpdating(InstallmentEntity installmentEntityToBeUpdate, InstallmentUpdateRequest installmentUpdateRequest) {
        installmentEntityToBeUpdate.setCustomerId(installmentUpdateRequest.getCustomerId());
        installmentEntityToBeUpdate.setSaleId(installmentUpdateRequest.getSaleId());
        installmentEntityToBeUpdate.setStatus(installmentUpdateRequest.getStatus());
        installmentEntityToBeUpdate.setInstallmentDate(installmentUpdateRequest.getInstallmentDate());
    }

    static InstallmentUpdateRequestToInstallmentEntityMapper initialize() {
        return Mappers.getMapper(InstallmentUpdateRequestToInstallmentEntityMapper.class);
    }

}

