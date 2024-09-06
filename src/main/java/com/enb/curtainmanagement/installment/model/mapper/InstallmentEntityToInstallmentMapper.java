package com.enb.curtainmanagement.installment.model.mapper;


import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.installment.model.Installment;
import com.enb.curtainmanagement.installment.model.entity.InstallmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InstallmentEntityToInstallmentMapper extends BaseMapper<InstallmentEntity, Installment> {

    @Override
    Installment map(InstallmentEntity source);

    static InstallmentEntityToInstallmentMapper initialize() {
        return Mappers.getMapper(InstallmentEntityToInstallmentMapper.class);
    }

}

